package com.khaki.cookingidea.ui.screen.main

import android.content.Context
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
import com.khaki.cookingidea.core.android.ContextSupplier
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme
import kotlinx.coroutines.launch

class ConditionEditContentActivity : ComponentActivity() {

    private val viewModel: ConditionEditContentViewModel by viewModels {
        ConditionEditContentViewModel.Factory(contextSupplier = object : ContextSupplier {
            override fun getContext(): Context {
                return this@ConditionEditContentActivity
            }
        })
    }

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
                ConditionEditContentScreen(
                    uiState = uiState,
                    dialogUiState = dialogUiState,
                    onClickSelectImage = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onClickFlyerImage = {
                        // 画像のプレビューに遷移する
                    },
                    onClickRemoveImage = {
                        viewModel.removeSelectedImage()
                    },
                    onClickGenerator = {
                        loadImage(uiState.selectedImageUri!!)
                    },
                    onClickIngredient = {
                        // ダイアログを表示する
                    },
                    onClickAddIngredient = {},
                    onClickAddIngredientFromFlyer = {},
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
                onStart = { _ ->
                    // NOP
                },
                onSuccess = { result ->
                    lifecycleScope.launch {
                        viewModel.execute((result as BitmapDrawable).bitmap)
                    }
                },
                onError = { _ ->
                    // NOP
                }
            )
            .build()
        imageLoader.enqueue(request)
    }
}
