package com.app.asakatsuyaruzo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.app.asakatsuyaroze.data.Alarm
import com.app.asakatsuyaruzo.data.getNextDateTimeAlarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


// Schedules all the alarm of the object at once including repeating ones
fun scheduleAlarm(context: Context): Boolean {
//
//    //一旦アラーム削除
//    val alarmManagerCancel = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val intentCancel = Intent(context, AlarmBroadcastReceiver::class.java)
//    val pendingIntent = PendingIntent.getBroadcast(
//        context,
//        0,
//        intentCancel,
//        PendingIntent.FLAG_MUTABLE
//    )
//
//    pendingIntent.cancel();
//    alarmManagerCancel.cancel(pendingIntent);

    Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■","GlobalScope.launchに入る前")

    //アラーム再設定
    GlobalScope.launch(Dispatchers.IO) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        var nextAlarm: Alarm? = getNextDateTimeAlarm()

        if(nextAlarm==null){

            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■","nextAlarmはNullだった")
        }else{
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■","nextAlarmはNullじゃなかった")
            //TODO PendingIntent.FLAG_CANCEL_CURRENTでキャンセルも一気にできるようにしたいが、複数FLG設定はどうやるのか？
            val pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■","nextAlarmはNullじゃなかった２")
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextAlarm!!.nextDateInLong!!, pendingIntent)
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextAlarm!!.nextDateInLong!!, pendingIntent)
            }

            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■","nextAlarmはNullじゃなかった３")
            var calendar = Calendar.getInstance()
            calendar.setTimeInMillis(nextAlarm!!.nextDateInLong!!)
            var nextTimeText= SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(calendar.getTime())
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■",nextTimeText)
        }

    }
    return true
}