package com.app.asakatsuyaruzo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaruzo.common.CommonSpaceBasicHorizontal6
import com.app.asakatsuyaruzo.ui.theme.*
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
    var selected:Boolean=false
    val context = LocalContext.current

    when(dayName){
        "monday" -> selected=alarmPattern.monday
        "tuesday" -> selected=alarmPattern.tuesday
        "wednesday" -> selected=alarmPattern.wednesday
        "thursday" -> selected=alarmPattern.thursday
        "friday" -> selected=alarmPattern.friday
        "saturday" -> selected=alarmPattern.saturday
        "sunday" -> selected=alarmPattern.sunday
    }

    Surface(
        modifier = Modifier
            .size(30.dp, 30.dp)
            .toggleable(
                value = selected,
                onValueChange = {
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

                        scheduleAlarm(context)
                    }
                }
            ),
        shape = CircleShape,
        color = if (selected) CommonColorSecondary else CommonColorTertiary,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayName.substring(0, 1).uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = if (selected) Color.White else Color.Black,
            )
        }
    }
}