package com.qralarm.app.data.local

import androidx.room.TypeConverter

/**
 * SQLite (and therefore Room) has no native "list" column type. This class
 * teaches Room how to flatten [Alarm.repeatDays] into a single comma
 * separated String column when writing, and how to parse it back into a
 * List<Int> when reading. Room discovers these methods automatically because
 * [AppDatabase] is annotated with @TypeConverters(Converters::class) - we
 * never call fromDayList/toDayList ourselves.
 */
class Converters {

    @TypeConverter
    fun fromDayList(days: List<Int>): String = days.joinToString(separator = ",")

    @TypeConverter
    fun toDayList(data: String): List<Int> =
        if (data.isBlank()) {
            emptyList()
        } else {
            data.split(",").map { it.trim().toInt() }
        }
}
