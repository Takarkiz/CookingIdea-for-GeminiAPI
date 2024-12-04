package com.khaki.cookingidea.ui.screen.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.khaki.cookingidea.ui.screen.selecttheme.SelectThemeActivity
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CookingIdeaTheme {
                StartScreen(
                    onClickStart = {
                        startActivity(
                            SelectThemeActivity.newIntent(this)
                        )
                    }
                )
            }
        }
    }
}
