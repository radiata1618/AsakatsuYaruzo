package com.app.asakatsuyaruzo.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import com.app.asakatsuyaruzo.MainActivity.Companion.mainAlarmPatternList
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
fun AlarmPatternListUnit(alarmPattern: AlarmPattern,
                         navController: NavController) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Text(
            text =alarmPattern.patternName,
            modifier =  Modifier.clickable {
                navController.navigate(
                    "setAlarmPatternUI/" + alarmPattern.id.toString()
                )

            }
        )
        Button(onClick = {
            GlobalScope.launch(Dispatchers.IO) {
                alarmPatternDao.delete(alarmPattern.id)
            }

        }) {
            Icon(Icons.Default.Delete, contentDescription = "削除")
        }
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
