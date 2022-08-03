package com.app.asakatsuyaroze.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val patternId: Int,
    val alarmType: Int,//1:初回起床,2:2度寝終了,3:出勤時間
    val hour: Int ,
    val minute: Int,
    val valid: Boolean,
    val nextHour: Int,
    val nexMinute: Int,
)