package com.app.asakatsuyaruzo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.app.asakatsuyaroze.data.Alarm
import com.app.asakatsuyaruzo.data.getNextDateTimeAlarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


// Schedules all the alarm of the object at once including repeating ones
fun scheduleAlarm(context: Context): Boolean {

    val alarm = Intent(context, AlarmBroadcastReceiver::class.java)

    GlobalScope.launch(Dispatchers.IO) {
        var nextAlarm: Alarm? = getNextDateTimeAlarm()
        if(nextAlarm==null){

        }else{
            val pendingIntent = PendingIntent.getBroadcast(
                context, nextAlarm!!.id, alarm, PendingIntent.FLAG_CANCEL_CURRENT)

                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextAlarm!!.nextDateInLong!!, pendingIntent)
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextAlarm!!.nextDateInLong!!, pendingIntent)
            }
        }
    }
    return true
}