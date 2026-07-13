package com.qralarm.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * The app's single Room database. We use the standard thread-safe singleton
 * pattern (double-checked locking on [INSTANCE]) so the Compose UI, the
 * AlarmReceiver, and the foreground AlarmService - three completely
 * different entry points into the process - all share one open connection
 * to qr_alarm.db instead of racing to create their own.
 *
 * Call [AppDatabase.getInstance] with an application Context (never an
 * Activity/Service Context) from anywhere that needs the DAO.
 */
@Database(entities = [Alarm::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "qr_alarm.db"
                ).build().also { INSTANCE = it }
            }
    }
}
