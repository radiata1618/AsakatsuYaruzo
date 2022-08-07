package com.app.asakatsuyaruzo

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmDao
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import com.app.asakatsuyaruzo.common.CommonSpaceBasicVertical
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.app.asakatsuyaroze.data.defaultAlarmPattern

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SetAlarmPatternUI(
    navController: NavController,
    patternId: Int
) {
    Scaffold(
    ) {

        var patternName by rememberSaveable { mutableStateOf("") }
        var alarmType1hour by rememberSaveable { mutableStateOf("") }
        var alarmType1minute by rememberSaveable { mutableStateOf("") }
        var alarmType2hour by rememberSaveable { mutableStateOf("") }
        var alarmType2minute by rememberSaveable { mutableStateOf("") }
        var alarmPatternData by rememberSaveable { mutableStateOf(defaultAlarmPattern()) }
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var mHour:Int=0
        var mMinute:Int=0
        val mCalendar = Calendar.getInstance()

        var showDialog by remember { mutableStateOf(false) }
        var result by remember { mutableStateOf("Result") }


        alarmPatternDao.getAlarmPatternLiveData(patternId).observe(lifecycleOwner) {

            GlobalScope.launch(Dispatchers.IO) {
                alarmPatternData = it
                patternName = alarmPatternData.patternName

                var alarmDataType1List = alarmDao.getAlarmByPatternIdType1(patternId)
                var alarmDataType2List = alarmDao.getAlarmByPatternIdType2(patternId)
                var alarmDataType3List = alarmDao.getAlarmByPatternIdType3(patternId)

                if(alarmDataType1List.isEmpty()){
                    mHour = mCalendar[Calendar.HOUR_OF_DAY]
                    mMinute = mCalendar[Calendar.MINUTE]
                }else{
                    alarmType1hour=alarmDataType1List[0].hour.toString()
                    alarmType1minute=alarmDataType1List[0].minute.toString()
                    alarmType2hour=alarmDataType2List[0].hour.toString()
                    alarmType2minute=alarmDataType2List[0].minute.toString()
                    mHour=alarmDataType2List[0].hour
                    mMinute=alarmDataType2List[0].minute
                }
            }

        }

        // Value for storing time as a string
        val mTime = remember { mutableStateOf("") }

        // Creating a TimePicker dialod
        val mTimePickerDialog = TimePickerDialog(
            context,
            { _, mHour: Int, mMinute: Int ->
                GlobalScope.launch(Dispatchers.IO) {
                    alarmDao.updateAlarmType2Default(patternId, mHour, mMinute)
                    mCalendar.set(0, 0, 0, mHour, mMinute)
                    mCalendar.add(Calendar.MINUTE, -5)
                    alarmDao.updateAlarmType1Default(
                        patternId,
                        mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE)
                    )
                }
            },
            mHour, mMinute, false
        )

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CommonSpaceBasicVertical()

            BasicTextField(
                value = patternName,
                onValueChange = { patternName = it },
                modifier = Modifier.padding(20.dp),
                singleLine = true,
                decorationBox = {
                    Box(
//                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(16.dp)
                    ) {
                        it()
                    }
                }
            )
            CommonSpaceBasicVertical()

            CommonDayOfWeekButtons(alarmPatternData)

            CommonSpaceBasicVertical()
            Text(text = "最初のアラーム")
            Text(text = alarmType1hour+":"+alarmType1minute)
            Text(text = "２回目のアラーム")
            Text(text = alarmType2hour+":"+alarmType2minute)
            Button(
                modifier = Modifier.size(134.dp, 50.dp),
                shape = RoundedCornerShape(100), // こっちは角丸にしてくれるやつ
                elevation = null, // これが影を消してくれる
                onClick = {
                    mTimePickerDialog.show()
                },
            ) {
                Text(text = "朝活用アラーム設定")
            }
            CommonSpaceBasicVertical()
            ElevatedButton(
                modifier = Modifier.size(134.dp, 50.dp),
                shape = RoundedCornerShape(100), // こっちは角丸にしてくれるやつ
                elevation = null, // これが影を消してくれる
                onClick = {

                    GlobalScope.launch(Dispatchers.IO) {

                        alarmPatternDao.updateDefault(
                            patternId,
                            patternName,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            "patternName"
                        )

                        showDialog = true

                        GlobalScope.launch(Dispatchers.Main) {
                            navController.navigateUp()
                        }
                    }
                },
            ) {
                Text(text = "Save")
            }
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