package com.app.asakatsuyaruzo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "alarmPattern")
data class AlarmPattern (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val patternName: String,
    val monday: Boolean,
    val tuesday: Boolean,
    val wednesday: Boolean,
    val thursday: Boolean,
    val friday: Boolean,
    val saturday: Boolean,
    val sunday: Boolean,
    val goToBedTimeHour: Int,
    val goToBedTimeMinute: Int,
    val forceGoToBedEnable: Boolean,
): Parcelable

fun defaultAlarmPattern(): AlarmPattern {
    return AlarmPattern(0,
        "",
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        0,
        0,
        false
    )
}

