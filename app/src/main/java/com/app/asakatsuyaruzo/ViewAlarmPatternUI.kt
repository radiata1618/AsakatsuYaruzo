package com.app.asakatsuyaruzo.ui.theme

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import com.app.asakatsuyaruzo.MainActivity.Companion.mainAlarmPatternList
import com.app.asakatsuyaruzo.common.CommonSpaceBasicHorizontal6
import com.app.asakatsuyaruzo.common.CommonSpaceBasicVertical6
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAlarmPatternUI(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Result") }

    Scaffold(floatingActionButton = { MainFloatingActionButton(navController) }) {

        AlarmPatternList(navController)
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    result = "Dismiss"
                    showDialog = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            result = "OK"
                            showDialog = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            result = "Cancel"
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                title = {
                    Text("AlertDialog")
                },
                text = {
                    Text("更新しました")
                },
            )
        }
    }
}

@Composable
fun AlarmPatternList(navController: NavController) {
    LazyColumn {
        items(mainAlarmPatternList) { alarmPatternData ->
            AlarmPatternListUnit(
                alarmPatternData,
                navController
            )
        }
    }
}


@Composable
fun AlarmPatternListUnit(
    alarmPattern: AlarmPattern,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                text = alarmPattern.patternName,
                modifier = Modifier.clickable {
                    navController.navigate(
                        "setAlarmPatternUI/" + alarmPattern.id.toString()
                    )
                }
            )
            CommonSpaceBasicVertical6()
            DayOfWeekButtons(alarmPattern)
        }
        Button(modifier = Modifier.size(50.dp, 50.dp),
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            onClick = {
            GlobalScope.launch(Dispatchers.IO) {
                alarmPatternDao.delete(alarmPattern.id)
            }

        }) {
            Icon(Icons.Default.Delete, contentDescription = "削除")
        }
    }

}

@Composable
fun DayOfWeekButtons(alarmPattern: AlarmPattern) {
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
    var buttonColor:Color
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

    if(buttonEnable){
        buttonColor=Color.Red
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
                alarmPatternDao.updateDefault(
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

@Composable
fun MainFloatingActionButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate("addAlarmPatternUI")
    }) {
        Icon(Icons.Filled.Add, contentDescription = "追加")
    }
}
