package com.app.asakatsuyaruzo.ui.theme

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.asakatsuyaruzo.data.AlarmPattern
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.app.asakatsuyaruzo.common.CommonSpaceBasicVertical6
import com.app.asakatsuyaruzo.CommonDayOfWeekButtons
import com.app.asakatsuyaruzo.data.getNextDateTimeAlarm
import kotlinx.coroutines.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAlarmPatternUI(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Result") }
    var nextTimeLong:Long by remember { mutableStateOf(0) }
    var nextTimeText by remember { mutableStateOf("") }
    var mainAlarmPatternList = remember {mutableStateListOf<AlarmPattern>()}
    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(floatingActionButton = { MainFloatingActionButton(navController) }) {
        alarmPatternDao.getAllLiveData().observe(lifecycleOwner) {
            mainAlarmPatternList.clear()
            mainAlarmPatternList.addAll(it)

            GlobalScope.launch(Dispatchers.IO) {
                var alarmNextDate = getNextDateTimeAlarm()
                if(alarmNextDate==null){
                    nextTimeLong = 0
                    nextTimeText= ""
                }else{
                    nextTimeLong = alarmNextDate.nextDateInLong!!
                    var calendar = Calendar.getInstance()
                    calendar.setTimeInMillis(nextTimeLong)
                    nextTimeText= SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(calendar.getTime())
                }

            }
        }

        Column() {
            Text(text = nextTimeText)
            AlarmPatternList(navController,mainAlarmPatternList)
        }

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
fun AlarmPatternList(navController: NavController,
                     mainAlarmPatternList:List<AlarmPattern>) {
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
    Surface(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(20.dp)
            ),
    ){
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
                CommonDayOfWeekButtons(alarmPattern)
            }
            Surface(
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        GlobalScope.launch(Dispatchers.IO) {
                            alarmPatternDao.delete(alarmPattern.id)
                        }
                    },
                shape = CircleShape,
                color = CommonColorSecondary
            ){
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "削除")
                }
            }

        }}

}


@Composable
fun MainFloatingActionButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate("addAlarmPatternUI")
    }) {
        Icon(Icons.Filled.Add, contentDescription = "追加")
    }
}
