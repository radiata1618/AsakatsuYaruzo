package com.app.asakatsuyaroze.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmPatternDao {
    @Insert
    fun insert(alarmPattern : AlarmPattern):Long

    @Update
    fun update(alarmPattern : AlarmPattern)

    @Delete
    fun delete(alarmPattern : AlarmPattern)

    @Query("delete from alarmPattern")
    fun deleteAll()

    @Query("select * from alarmPattern")
    fun getAll(): List<AlarmPattern>

    @Query("SELECT * FROM alarmPattern ORDER BY id ASC")
    fun getAllLiveData(): LiveData<List<AlarmPattern>>

    @Query("select * from alarmPattern where id = :id")
    fun getAlarmPattern(id: Int): AlarmPattern
}