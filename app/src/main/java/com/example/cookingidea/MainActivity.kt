package com.example.cookingidea

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.cookingidea.ui.theme.CookingIdeaTheme
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory() }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) return@registerForActivityResult
            viewModel.updateSelectedImageUri(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val uiState by viewModel.uiStateFlow.collectAsState()

            CookingIdeaTheme {
                MainScreen(
                    uiState = uiState,
                    onClickSelectImage = {
                        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                    onClickGenerator = {
                        loadImage(uiState.selectedImageUri!!)
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