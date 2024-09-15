package com.khaki.cookingidea.ui.screen.main

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.ai.client.generativeai.type.content
import com.khaki.cookingidea.BuildConfig
import com.khaki.cookingidea.R
import com.khaki.cookingidea.core.android.ContextSupplier
import com.khaki.cookingidea.model.GeminiModelHelper
import com.khaki.cookingidea.ui.screen.menudialog.MenuDialogUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val contextSupplier: ContextSupplier
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(MainUiState())
    val uiStateFlow: StateFlow<MainUiState> = _uiStateFlow.asStateFlow()

    private val _menuDialogUiStateFlow = MutableStateFlow(MenuDialogUiState())
    val menuDialogUiStateFlow: StateFlow<MenuDialogUiState> = _menuDialogUiStateFlow.asStateFlow()

    private val model = GeminiModelHelper.Build()
        .setModel(GeminiModelHelper.ModelType.FLASH)
        .setApiKey(BuildConfig.gemini_api_key)
        .setResponseType(GeminiModelHelper.ResponseType.TEXT)
        .build()

    fun updateSelectedImageUri(uri: Uri) {
        _uiStateFlow.update { it.copy(selectedImageUri = uri) }
    }

    fun dismissDialog() {
        _uiStateFlow.update { it.copy(showDialog = false) }
    }

    fun removeSelectedImage() {
        _uiStateFlow.update { it.copy(selectedImageUri = null) }
    }

    suspend fun execute(image: Bitmap) {

        _uiStateFlow.update {
            it.copy(isLoading = true)
        }

        val chat = model.startChat()
        val response = chat.sendMessage(
            content {
                image(image)
                text(contextSupplier.getContext().getString(R.string.main_prompt))
            }
        )

        _uiStateFlow.update {
            it.copy(
                isLoading = false,
                showDialog = true,
            )
        }

        _menuDialogUiStateFlow.update {
            it.copy(
                generatedText = response.text ?: ""
            )
        }
    }

    class Factory(private val contextSupplier: ContextSupplier) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(contextSupplier) as T
        }
    }
}
