package com.app.asakatsuyaruzo

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaruzo.common.CommonSpaceBasicHorizontal6
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun CommonDayOfWeekButtons(alarmPattern: AlarmPattern) {
    Row() {
        DayOfWeekButtonUnit("monday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("tuesday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("wednesday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("thursday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("friday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("saturday",alarmPattern)
        CommonSpaceBasicHorizontal6()
        DayOfWeekButtonUnit("sunday",alarmPattern)
    }
}

@Composable
fun DayOfWeekButtonUnit(dayName: String,alarmPattern: AlarmPattern) {
    var buttonColor: Color
    var buttonEnable:Boolean=false
    when(dayName){
        "monday" -> buttonEnable=alarmPattern.monday
        "tuesday" -> buttonEnable=alarmPattern.tuesday
        "wednesday" -> buttonEnable=alarmPattern.wednesday
        "thursday" -> buttonEnable=alarmPattern.thursday
        "friday" -> buttonEnable=alarmPattern.friday
        "saturday" -> buttonEnable=alarmPattern.saturday
        "sunday" -> buttonEnable=alarmPattern.sunday
    }

    Log.d("D/■■■■■■■■■■■■■■■■■■■■■■■■■AADDD",buttonEnable.toString())
    Log.d("■■■■■■■■■■■■■111あいでぃー",alarmPattern.id.toString())

    if(buttonEnable){
        buttonColor= Color.Red
    }else{
        buttonColor = Color.Gray

    }

    Button(
        modifier = Modifier
            .size(30.dp, 30.dp)
            .padding(all = 0.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        onClick = {
            var monday:Boolean?=alarmPattern.monday
            var tuesday:Boolean?=alarmPattern.tuesday
            var wednesday:Boolean?=alarmPattern.wednesday
            var thursday:Boolean?=alarmPattern.thursday
            var friday:Boolean?=alarmPattern.friday
            var saturday:Boolean?=alarmPattern.saturday
            var sunday:Boolean?=alarmPattern.sunday
            when(dayName) {
                "monday" -> monday=!alarmPattern.monday
                "tuesday" -> tuesday=!alarmPattern.tuesday
                "wednesday" -> wednesday=!alarmPattern.wednesday
                "thursday" -> thursday=!alarmPattern.thursday
                "friday" -> friday=!alarmPattern.friday
                "saturday" -> saturday=!alarmPattern.saturday
                "sunday" -> sunday=!alarmPattern.sunday
            }

            GlobalScope.launch(Dispatchers.IO) {
                MainActivity.alarmPatternDao.updateDefault(
                    alarmPattern.id,
                    null,
                    monday,
                    tuesday,
                    wednesday,
                    thursday,
                    friday,
                    saturday,
                    sunday,
                    null,
                    null,
                    null,
                    "dayOfWeek",
                )
            }
        },
    ) {
        Text(dayName.substring(0, 1).uppercase())
    }
}