package com.qralarm.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a single alarm. One row in the "alarms" table
 * maps to exactly one instance of this class.
 *
 * - [hour] / [minute] are stored in 24-hour format (0-23 / 0-59). Keeping the
 *   trigger time as two plain Ints (instead of a Date/Instant) means we don't
 *   need a type converter for it, and it trivially maps onto AlarmManager's
 *   Calendar-based scheduling API later.
 * - [repeatDays] holds the days of week this alarm repeats on, using
 *   java.time.DayOfWeek.getValue() numbering (1 = Monday ... 7 = Sunday). An
 *   empty list means "fires once, then disables itself".
 * - [qrCodePayload] is the raw text decoded from the QR code the user scanned
 *   during setup for this alarm. When the alarm rings, the mission screen
 *   scans a QR code and compares its payload against this value - the alarm
 *   can only be dismissed on an exact match. If null, the alarm has not had a
 *   QR code assigned yet (the UI will require one before it can be enabled).
 */
@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val hour: Int,
    val minute: Int,
    val label: String = "",
    val isEnabled: Boolean = true,
    val repeatDays: List<Int> = emptyList(),
    val qrCodePayload: String? = null,
    val vibrate: Boolean = true,
    val soundUri: String? = null
)
