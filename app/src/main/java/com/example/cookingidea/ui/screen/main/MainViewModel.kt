package com.example.cookingidea.ui.screen.main

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingidea.BuildConfig
import com.example.cookingidea.ui.screen.menudialog.MenuDialogUiState
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// TODO: ViewModelの状態クラスを利用したい
private data class MainViewModelState(
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val generatedText: String = "",
)

class MainViewModel : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(MainUiState())
    val uiStateFlow: StateFlow<MainUiState> = _uiStateFlow.asStateFlow()

    private val _menuDialogUiStateFlow = MutableStateFlow(MenuDialogUiState())
    val menuDialogUiStateFlow: StateFlow<MenuDialogUiState> = _menuDialogUiStateFlow.asStateFlow()

    private val model = GenerativeModel(
        "gemini-1.5-flash",
        BuildConfig.gemini_api_key,
        generationConfig = generationConfig {
            temperature = 1f
            topK = 64
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        },
        // safetySettings = Adjust safety settings
        // See https://ai.google.dev/gemini-api/docs/safety-settings
    )

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
                text("添付したチラシに載っている食材を3つ以上利用した、栄養バランスの取れた献立を考えてください\nただし献立は以下の要件を満たしてください\n- 献立のなかで合計3つ以上の食材がこのチラシから参照されていれば大丈夫です\n- 調味料に関してはチラシの内容を考慮する必要はありません。全ての調味料がすでに揃っている前提で献立を考えてください\n- チラシの中で特に安くなっているものを優先して利用してください")
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

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}