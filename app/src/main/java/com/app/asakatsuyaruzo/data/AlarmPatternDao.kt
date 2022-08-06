package com.app.asakatsuyaroze.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmPatternDao {
    @Insert
    fun insert(alarmPattern: AlarmPattern): Long

    @Update
    fun update(alarmPattern: AlarmPattern)

    @Query("delete from alarmPattern where id = :id")
    fun delete(id: Int)

    @Query("delete from alarmPattern")
    fun deleteAll()

    @Query("select * from alarmPattern")
    fun getAll(): List<AlarmPattern>

    @Query("SELECT * FROM alarmPattern ORDER BY id ASC")
    fun getAllLiveData(): LiveData<List<AlarmPattern>>

    @Query("select * from alarmPattern where id = :id")
    fun getAlarmPattern(id: Int): AlarmPattern

    fun insertDefault(patternName: String): Long {
        val newAlarmPattern: AlarmPattern = AlarmPattern(
            0,
            patternName,
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
        val alarmPatternId: Long = insert(newAlarmPattern)
        return alarmPatternId
    }

    fun updateDefault(
        idInput: Int,
        patternNameInput: String?,
        mondayInput: Boolean?,
        tuesdayInput: Boolean?,
        wednesdayInput: Boolean?,
        thursdayInput: Boolean?,
        fridayInput: Boolean?,
        saturdayInput: Boolean?,
        sundayInput: Boolean?,
        goToBedTimeHourInput: Int?,
        goToBedTimeMinuteInput: Int?,
        forceGoToBedEnableInput: Boolean?,
        updatePattern:String
    ) {

        var alarmPattern: AlarmPattern = getAlarmPattern(idInput)

        var id: Int=alarmPattern.id
        var patternName: String=alarmPattern.patternName
        var monday: Boolean=alarmPattern.monday
        var tuesday: Boolean=alarmPattern.tuesday
        var wednesday: Boolean=alarmPattern.wednesday
        var thursday: Boolean=alarmPattern.thursday
        var friday: Boolean=alarmPattern.friday
        var saturday: Boolean=alarmPattern.saturday
        var sunday: Boolean=alarmPattern.sunday
        var goToBedTimeHour: Int=alarmPattern.goToBedTimeHour
        var goToBedTimeMinute: Int=alarmPattern.goToBedTimeMinute
        var forceGoToBedEnable: Boolean=alarmPattern.forceGoToBedEnable

        if(updatePattern=="patternName"){
            patternName=patternNameInput!!

        }else if(updatePattern=="dayOfWeek"){
            var monday: Boolean=mondayInput!!
            var tuesday: Boolean=tuesdayInput!!
            var wednesday: Boolean=wednesdayInput!!
            var thursday: Boolean=thursdayInput!!
            var friday: Boolean=fridayInput!!
            var saturday: Boolean=saturdayInput!!
            var sunday: Boolean=sundayInput!!
            Log.d("■■■■■■■■AA■■■■■■■■AA", "SSSSS$monday")
        }

        update(AlarmPattern(
            id,
            patternName,
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday,
            sunday,
            goToBedTimeHour,
            goToBedTimeMinute,
            forceGoToBedEnable))

    }
}