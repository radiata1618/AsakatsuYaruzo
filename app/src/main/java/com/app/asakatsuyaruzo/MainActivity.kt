package com.app.asakatsuyaruzo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Nullable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.app.asakatsuyaruzo.data.AlarmDao
import com.app.asakatsuyaroze.data.AlarmPatternDao
import com.app.asakatsuyaroze.data.AppDatabase
import com.app.asakatsuyaruzo.ui.theme.AsakatsuYaruzoTheme
import com.app.asakatsuyaruzo.ui.theme.ViewAlarmPatternUI

class MainActivity : ComponentActivity() {
    private val REQUEST_PERMISSION_CODE = 1

    companion object {
        lateinit var database: AppDatabase
        lateinit var alarmPatternDao: AlarmPatternDao
        lateinit var alarmDao: AlarmDao
//        var mainAlarmPatternList = mutableStateListOf<AlarmPattern>()

    }

    // SYSTEM_ALERT_WINDOWが許可されているかのチェック
    fun isGranted(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    // SYSTEM_ALERT_WINDOWの許可をリクエストする
    private fun requestPermission() {
        if (Settings.canDrawOverlays(this)) {
            // 許可されたときの処理
        } else {
            val uri: Uri = Uri.parse("package:$packageName")
            val intent: Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
            startActivityForResult(intent, REQUEST_PERMISSION_CODE)
        }
    }
    // 許可されたかの確認は、onActivityResultでチェックする
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // 許可されたときの処理
            } else {
                // 拒否されたときの処理
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
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


        requestPermission()
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

