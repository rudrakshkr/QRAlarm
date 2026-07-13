package com.qralarm.app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the "alarms" table. This is the *only* class in the
 * app allowed to contain SQL. Everything above it (Repository, ViewModels,
 * the alarm-triggering BroadcastReceiver, the foreground Service) goes
 * through these methods rather than touching SQLite directly.
 *
 * Read queries return Flow<...>. Compose screens collect these as State, so
 * the alarm list UI automatically recomposes the instant a row changes -
 * no manual refresh logic anywhere else in the app.
 */
@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms ORDER BY hour, minute")
    fun observeAlarms(): Flow<List<Alarm>>

    @Query("SELECT * FROM alarms WHERE id = :id")
    fun observeAlarmById(id: Long): Flow<Alarm?>

    @Query("SELECT * FROM alarms WHERE id = :id")
    suspend fun getAlarmById(id: Long): Alarm?

    @Query("SELECT * FROM alarms WHERE isEnabled = 1")
    suspend fun getEnabledAlarms(): List<Alarm>

    /**
     * Insert a brand-new alarm, or overwrite an existing one if its [Alarm.id]
     * already exists (used by the edit screen). Returns the row id, which the
     * caller needs immediately after inserting a *new* alarm in order to
     * schedule it with AlarmManager using that id as the request code.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAlarm(alarm: Alarm): Long

    @Update
    suspend fun updateAlarm(alarm: Alarm)

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

    @Query("UPDATE alarms SET isEnabled = :isEnabled WHERE id = :id")
    suspend fun setEnabled(id: Long, isEnabled: Boolean)
}
