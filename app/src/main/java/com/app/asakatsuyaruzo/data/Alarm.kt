package com.app.asakatsuyaroze.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val patternId: Int,
    val alarmType: Int,
    val hour: Int,
    val minute: Int,
    val valid: Boolean,
    val nextDateInLong: Long?,
)