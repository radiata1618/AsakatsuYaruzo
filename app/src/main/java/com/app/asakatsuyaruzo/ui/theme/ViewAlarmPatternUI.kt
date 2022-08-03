package com.app.asakatsuyaruzo.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAlarmPatternUI(navController: NavController){
    Scaffold(floatingActionButton={MainFloatingActionButton(navController)}){

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