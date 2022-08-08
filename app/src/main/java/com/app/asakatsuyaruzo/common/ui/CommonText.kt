package com.app.asakatsuyaruzo.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.asakatsuyaruzo.common.logic.commonTranslateTimeIntToString


@Composable
fun CommonTextTimeShowLarge(hour:Int?,minute:Int?){
    Box(modifier = Modifier.padding(all=8.dp)
        .border(
            width = 0.5.dp,
            color = Color.DarkGray,
            shape = RoundedCornerShape(20.dp)
        )) {
        Text(text =commonTranslateTimeIntToString(hour,minute))
    }
}