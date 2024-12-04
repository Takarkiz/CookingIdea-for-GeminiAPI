package com.khaki.cookingidea.ui.screen.selecttheme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

class SelectThemeActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SelectThemeActivity::class.java)
        }
    }

    private val viewModel: SelectThemeViewModel by viewModels {
        SelectThemeViewModel.Factory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CookingIdeaTheme {
                SelectThemeScreen(
                    uiState = uiState,
                    onUpdateSelectedTheme = {
                        viewModel.updateInputTheme(it)
                    },
                    onBack = {
                        finish()
                    }
                )
            }
        }
    }
}
