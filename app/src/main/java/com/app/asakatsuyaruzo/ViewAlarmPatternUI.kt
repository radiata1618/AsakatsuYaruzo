package com.app.asakatsuyaruzo.ui.theme

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.asakatsuyaroze.data.AlarmPattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAlarmPatternUI(navController: NavController) {
    Scaffold(floatingActionButton = { MainFloatingActionButton(navController) }) {
        AlarmPatternList()
    }
}

@Composable
fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    content: LazyListScope.() -> Unit
) {
}
https://engawapg.net/jetpack-compose/1442/list/
@Composable
fun AlarmPatternList() {
    LazyColumn{
        item {
            AlarmPatternListUnit(
                AlarmPattern(
                    0,
                    "namename",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    0,
                    0,
                    false
                )
            )

        }
        item {
            AlarmPatternListUnit(
                AlarmPattern(
                    0,
                    "name2",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    0,
                    0,
                    false
                )
            )

        }
        item {
            AlarmPatternListUnit(
                AlarmPattern(
                    0,
                    "name3",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    0,
                    0,
                    false
                )
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
