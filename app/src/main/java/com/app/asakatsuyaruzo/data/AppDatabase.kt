package com.app.asakatsuyaroze.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Alarm::class,AlarmPattern::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
    abstract fun alarmPatternDao(): AlarmPatternDao
}