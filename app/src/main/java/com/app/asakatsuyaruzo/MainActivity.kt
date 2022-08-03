package com.app.asakatsuyaruzo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.asakatsuyaruzo.ui.theme.AsakatsuYaruzoTheme
import com.app.asakatsuyaruzo.ui.theme.ViewAlarmPatternUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    AddAlarmPatternUI(1,navController)
                }
            }
        }
    }
}

