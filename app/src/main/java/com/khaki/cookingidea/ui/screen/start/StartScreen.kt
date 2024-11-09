package com.khaki.cookingidea.ui.screen.start

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khaki.cookingidea.ui.screen.start.compose.StartContent
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun StartScreen(
    onClickStart: () -> Unit,
) {
    Scaffold {
        StartContent(
            modifier = Modifier.padding(it),
            onClickStart = {}
        )
    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    CookingIdeaTheme {
        StartScreen(
            onClickStart = {},
        )
    }
}
