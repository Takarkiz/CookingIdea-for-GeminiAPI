package com.khaki.cookingidea.ui.screen.start

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khaki.cookingidea.ui.screen.start.compose.StartContent

@Composable
fun StartScreen() {
    Scaffold {
        StartContent(
            modifier = Modifier.padding(it),
            onClickStart = {}
        )
    }
}
