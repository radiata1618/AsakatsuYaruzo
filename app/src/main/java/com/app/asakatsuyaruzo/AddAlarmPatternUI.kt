package com.app.asakatsuyaruzo

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.common.CommonSpaceBasicVertical
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddAlarmPatternUI(
//    index: Int,
    navController: NavController
) {

    Scaffold(
    ) {

        var patternName by rememberSaveable { mutableStateOf("") }
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.Top,
        ) {

            CommonSpaceBasicVertical()
            Text(
                text = "パターン名を入力してください"
            )
            CommonSpaceBasicVertical()
            BasicTextField(
                value = patternName,
                onValueChange = { patternName = it },
                modifier = Modifier.padding(20.dp),
                singleLine = true,
            )
            CommonSpaceBasicVertical()
            Button(
                modifier = Modifier.size(134.dp, 50.dp),
                shape = RoundedCornerShape(100), // こっちは角丸にしてくれるやつ
                elevation = null, // これが影を消してくれる
                onClick = {

                    val database =
                        Room.databaseBuilder(context, AppDatabase::class.java, "mainDatabase")
                            .build()

                    val alarmPatternDao = database.alarmPatternDao()

                    GlobalScope.launch(Dispatchers.IO) {
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
                        val alarmPatternId: Long = alarmPatternDao.insert(newAlarmPattern)

                        GlobalScope.launch(Dispatchers.Main) {
                            navController.navigate(
                                "setAlarmPatternUI/" + alarmPatternId.toInt().toString()
                            )
                        }
                    }
                }
            ) {
                Text(text = "完了")
            }
        }
    }
}

