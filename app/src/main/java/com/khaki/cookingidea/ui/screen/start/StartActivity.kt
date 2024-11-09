package com.khaki.cookingidea.ui.screen.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StartScreen(
                onClickStart = {
                    // テーマ選択画面へ遷移する
                }
            )
        }
    }
}
