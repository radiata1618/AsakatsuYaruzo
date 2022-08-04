package com.app.asakatsuyaruzo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.asakatsuyaruzo.common.CommonSpaceBasicVertical

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SetAlarmPatternUI(
    navController: NavController,
    patternId:Int
) {
    Scaffold(
    ) {

        var patternName by rememberSaveable { mutableStateOf("") }

        Column (
            modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
        ) {
            CommonSpaceBasicVertical()
            BasicTextField(
                value = patternName,
                onValueChange = { patternName = it },
                modifier = Modifier.padding(20.dp)
            )
            CommonSpaceBasicVertical()
            Button(
                modifier = Modifier.size(134.dp, 50.dp),
                shape = RoundedCornerShape(100), // こっちは角丸にしてくれるやつ
                elevation = null, // これが影を消してくれる
                onClick = {},
            ) {
                Text(text = "完了")
            }
        }
    }
}