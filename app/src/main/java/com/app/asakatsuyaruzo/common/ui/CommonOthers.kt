package com.app.asakatsuyaruzo.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonSpaceBasicVertical(){
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun CommonSpaceBasicVertical6(){
    Spacer(modifier = Modifier.height(6.dp))
}


@Composable
fun CommonSpaceBasicHorizontal(){
    Spacer(modifier = Modifier.width(16.dp))
}


@Composable
fun CommonSpaceBasicHorizontal6(){
    Spacer(modifier = Modifier.width(6.dp))
}