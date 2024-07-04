package com.example.cookingidea.ui.screen.main

import android.app.Application
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.cookingidea.ui.theme.CookingIdeaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory(Application()) }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) return@registerForActivityResult
            viewModel.updateSelectedImageUri(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val uiState by viewModel.uiStateFlow.collectAsState()
            val dialogUiState by viewModel.menuDialogUiStateFlow.collectAsState()

            CookingIdeaTheme {
                MainScreen(
                    uiState = uiState,
                    dialogUiState = dialogUiState,
                    onClickSelectImage = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onClickGenerator = {
                        loadImage(uiState.selectedImageUri!!)
                    },
                    onDismissDialogRequest = {
                        viewModel.dismissDialog()
                    }
                )
            }
        }
    }

    private fun loadImage(uri: Uri) {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()
        val request = ImageRequest.Builder(this)
            .data(uri)
            .target(
                onStart = { placeholder ->
                    // Handle the placeholder drawable.
                },
                onSuccess = { result ->
                    lifecycleScope.launch {
                        viewModel.execute((result as BitmapDrawable).bitmap)
                    }
                },
                onError = { error ->
                    // Handle the error drawable.
                }
            )
            .build()
        imageLoader.enqueue(request)
    }
}
