package com.app.asakatsuyaruzo.ui.theme

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.room.Room
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
import com.app.asakatsuyaruzo.MainActivity.Companion.database
import com.app.asakatsuyaruzo.MainActivity.Companion.mainAlarmPatternList
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAlarmPatternUI(navController: NavController) {


    Scaffold(floatingActionButton = { MainFloatingActionButton(navController) }) {

        AlarmPatternList(navController)
    }
}
//
//private fun loadAlarmPatternDataList() {
//    CoroutineScope(Dispatchers.Main).launch {
//        withContext(Dispatchers.Default) {
//            alarmPatternDao.getAll().forEach { todo ->
//                alarmPatternDataList.add(todo)
//            }
//        }
//    }
//}

@Composable
fun AlarmPatternList(navController: NavController) {
    LazyColumn {
        items(mainAlarmPatternList) { alarmPatternData ->
            AlarmPatternListUnit(
                alarmPatternData
            )
        }
    }
}


@Composable
fun AlarmPatternListUnit(alarmPattern: AlarmPattern) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Text(
            alarmPattern.patternName
        )
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
