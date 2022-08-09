package com.app.asakatsuyaruzo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.asakatsuyaroze.data.Alarm
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmDao
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import java.util.*

@Dao
interface AlarmDao {
    @Insert
    fun insert(alarm : Alarm)

    @Update
    fun update(alarm : Alarm)

    @Delete
    fun delete(alarm : Alarm)

    @Query("delete from alarm")
    fun deleteAll()

    @Query("select * from alarm order by nextDateInLong asc ")
    fun getAlarmListOrderByNextDate():List<Alarm>

    @Query("select * from alarm")
    fun getAll(): List<Alarm>

    @Query("select * from alarm where id = :p0")
    fun getLiveData(p0: Int): LiveData<Alarm>

    @Query("select * from alarm where id = :p0")
    fun get(p0: Int): Alarm

    @Query("delete from alarm where patternId = :p0 and alarmType = 1")
    fun deleteAlarmByPatternIdType1(p0: Int): Unit

    @Query("delete from alarm where patternId = :p0 and alarmType = 2")
    fun deleteAlarmByPatternIdType2(p0: Int): Unit

    @Query("delete from alarm where patternId = :p0 and alarmType = 3")
    fun deleteAlarmByPatternIdType3(p0: Int): Unit

    @Query("select * from alarm where patternId = :p0 and alarmType = 1")
    fun getAlarmByPatternIdType1(p0: Int): List<Alarm>

    @Query("select * from alarm where patternId = :p0 and alarmType = 2")
    fun getAlarmByPatternIdType2(p0: Int): List<Alarm>

    @Query("select * from alarm where patternId = :p0 and alarmType = 3")
    fun getAlarmByPatternIdType3(p0: Int): List<Alarm>

    @Query("select * from alarm where patternId = :p0")
    fun getAlarmByPatternId(p0: Int): List<Alarm>

    fun insertAlarmType1Default(patternId:Int,hour:Int,minute:Int){
        insert(Alarm(0, patternId,1,hour,minute,true, calcNextDateInLong(patternId,hour,minute)))
    }

    fun updateAlarmType1Default(patternId:Int,hour:Int,minute:Int){
        deleteAlarmByPatternIdType1(patternId)
        insertAlarmType1Default(patternId,hour,minute)
    }

    fun insertAlarmType2Default(patternId:Int,hour:Int,minute:Int){
        insert(Alarm(0, patternId,2,hour,minute,true, calcNextDateInLong(patternId,hour,minute)))
    }

    fun updateAlarmType2Default(patternId:Int,hour:Int,minute:Int){
        deleteAlarmByPatternIdType2(patternId)
        insertAlarmType2Default(patternId,hour,minute)
    }

    fun insertAlarmType3Default(patternId:Int,hour:Int,minute:Int){
        insert(Alarm(0, patternId,3,hour,minute,true, calcNextDateInLong(patternId,hour,minute)))
    }

    fun updateAlarmType3Default(patternId:Int,hour:Int,minute:Int){
        deleteAlarmByPatternIdType3(patternId)
        insertAlarmType3Default(patternId,hour,minute)
    }
}

fun calcNextDateInLong(patternId:Int,hour:Int,minute:Int):Long? {

    val alarmPattern: AlarmPattern = alarmPatternDao.getAlarmPattern(patternId)
    if (
        !alarmPattern.monday &&
        !alarmPattern.tuesday &&
        !alarmPattern.wednesday &&
        !alarmPattern.thursday &&
        !alarmPattern.friday &&
        !alarmPattern.saturday &&
        !alarmPattern.sunday
    ) {
        return null
    } else {
        var tmpCalendar = Calendar.getInstance()
        tmpCalendar.set(Calendar.HOUR_OF_DAY, hour)
        tmpCalendar.set(Calendar.MINUTE, minute)

        if(tmpCalendar.before(Calendar.getInstance())){
            tmpCalendar.add(Calendar.DAY_OF_MONTH,1)
        }

        while(!judgeDayOfWeekOnOff(tmpCalendar,alarmPattern)){
            tmpCalendar.add(Calendar.DAY_OF_MONTH,1)
        }
        return tmpCalendar.timeInMillis
    }
}


fun judgeDayOfWeekOnOff(calendar: Calendar, alarmPattern: AlarmPattern): Boolean {
    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> alarmPattern.monday
        Calendar.TUESDAY -> alarmPattern.tuesday
        Calendar.WEDNESDAY -> alarmPattern.wednesday
        Calendar.THURSDAY -> alarmPattern.thursday
        Calendar.FRIDAY -> alarmPattern.friday
        Calendar.SATURDAY -> alarmPattern.saturday
        else -> alarmPattern.sunday
    }
}

fun refleshNextDate(id:Int){

    var alarm:Alarm = alarmDao.get(id)
    alarmDao.update(Alarm(
        alarm.id,
        alarm.patternId,
        alarm.alarmType,
        alarm.hour,
        alarm.minute,
        alarm.valid,
        calcNextDateInLong(alarm.patternId,alarm.hour,alarm.minute)
    ))

}

fun getNextDateTimeAlarm ():Alarm?{
    var alarmListOrderByNextDateList = alarmDao.getAlarmListOrderByNextDate()
    if(alarmListOrderByNextDateList.isEmpty()){
        return null
    }
    if(alarmListOrderByNextDateList[0].nextDateInLong==null){
        return null
    }else{
        return alarmListOrderByNextDateList[0]
    }
}