package com.app.asakatsuyaruzo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.app.asakatsuyaroze.data.Alarm
import com.app.asakatsuyaruzo.data.getNextDateTimeAlarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDateTime
import java.util.*


class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        GlobalScope.launch(Dispatchers.IO) {
            var nextAlarm: Alarm? = getNextDateTimeAlarm()
            if(nextAlarm!=null){
                if(nextAlarm.nextDateInLong!! < Calendar.getInstance().timeInMillis){
                    val intent: Intent = Intent(context, MainActivity().javaClass)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    ContextCompat.startActivity(context, intent, null)//
                }
            }
        }
    }
}