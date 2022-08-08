package com.app.asakatsuyaruzo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.app.asakatsuyaruzo.data.AlarmDao
import com.app.asakatsuyaroze.data.AlarmPattern
import com.app.asakatsuyaroze.data.AlarmPatternDao
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.ui.theme.AsakatsuYaruzoTheme
import com.app.asakatsuyaruzo.ui.theme.ViewAlarmPatternUI

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var alarmPatternDao: AlarmPatternDao
        lateinit var alarmDao: AlarmDao
//        var mainAlarmPatternList = mutableStateListOf<AlarmPattern>()

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

