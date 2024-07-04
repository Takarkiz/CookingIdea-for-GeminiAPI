package com.example.cookingidea.ui.screen.main

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingidea.BuildConfig
import com.example.cookingidea.R
import com.example.cookingidea.model.GeminiModelHelper
import com.example.cookingidea.ui.screen.menudialog.MenuDialogUiState
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(private val application: Application) : AndroidViewModel(application) {

    private val _uiStateFlow = MutableStateFlow(MainUiState())
    val uiStateFlow: StateFlow<MainUiState> = _uiStateFlow.asStateFlow()

    private val _menuDialogUiStateFlow = MutableStateFlow(MenuDialogUiState())
    val menuDialogUiStateFlow: StateFlow<MenuDialogUiState> = _menuDialogUiStateFlow.asStateFlow()

    private val context get() = application.applicationContext

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

    suspend fun execute(image: Bitmap) {

        _uiStateFlow.update {
            it.copy(isLoading = true)
        }

        val chat = model.startChat()
        val response = chat.sendMessage(
            content {
                image(image)
                text(context.getString(R.string.core_prompt))
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

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}
