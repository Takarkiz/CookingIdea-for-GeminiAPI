package com.khaki.cookingidea.ui.screen.flyer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.khaki.cookingidea.model.MenuRequestParam
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

class FlyerActivity : ComponentActivity() {

    companion object {
        fun newIntent(
            context: Context,
            theme: String?,
        ): Intent {
            return Intent(context, FlyerActivity::class.java)
                .putExtra(EXTRA_KEY_THEME, theme)
        }

        private const val EXTRA_KEY_THEME = "EXTRA_KEY_THEME"
    }

    private val viewModel: FlyerViewModel by viewModels {
        FlyerViewModel.Factory()
    }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                // Handle photo
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val theme = intent.getStringExtra(EXTRA_KEY_THEME)
        val requestBuilder = MenuRequestParam.Builder()
        if (theme != null) {
            requestBuilder.setRequestMenuTheme(theme)
        }
        viewModel.setInitialRequestParam(
            requestBuilder.build()
        )

        setContent {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CookingIdeaTheme {
                FlyerScreen(
                    uiState = uiState,
                    onSelectUploadingButton = {
//                    photoPickerLauncher.launch()
                    },
                    onBack = {
                        finish()
                    },
                )
            }
        }
    }
}
