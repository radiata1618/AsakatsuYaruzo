package com.app.asakatsuyaruzo

import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.app.asakatsuyaroze.data.AlarmDao
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaroze.data.AlarmPatternDao
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.ui.theme.AlarmPatternList
import com.app.asakatsuyaruzo.ui.theme.AsakatsuYaruzoTheme
import com.app.asakatsuyaruzo.ui.theme.ViewAlarmPatternUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var alarmPatternDao: AlarmPatternDao
        lateinit var alarmDao: AlarmDao
        var mainAlarmPatternList = mutableStateListOf<AlarmPattern>()

        lateinit var setAlarmPattern : AlarmPattern
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mainDatabase"
        ).build()

        alarmPatternDao = database.alarmPatternDao()
        alarmDao = database.alarmDao()

        alarmPatternDao.getAllLiveData().observe(this, Observer{
            mainAlarmPatternList.clear()
            mainAlarmPatternList.addAll(it)
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■AA",it[0].monday.toString())
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■AA",it[0].tuesday.toString())
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■AA",it[0].wednesday.toString())
            Log.d("■■■■■■■■■■■■■■■■■■■■■■■■■AA",it[0].thursday.toString())
//            AlarmPatternList(alarmPatternDataList = it, navController = navController)
        })

        setContent {
            MainScreenUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(){
    AsakatsuYaruzoTheme {

        val navController = rememberNavController()//画面遷移のため

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = "viewAlarmPatternUI") {
                composable("viewAlarmPatternUI") {
                    ViewAlarmPatternUI(navController)
                }

                composable("addAlarmPatternUI") {
                    AddAlarmPatternUI(navController)
                }

                composable("setAlarmPatternUI/{patternId}",

                ) { backStackEntry ->
                    SetAlarmPatternUI(navController, backStackEntry.arguments?.getString("patternId")!!.toInt())
                }
            }
        }
    }
}

