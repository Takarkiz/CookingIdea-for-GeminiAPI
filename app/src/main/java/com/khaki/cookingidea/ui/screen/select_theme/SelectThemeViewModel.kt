package com.khaki.cookingidea.ui.screen.select_theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khaki.cookingidea.ui.screen.select_theme.compose.SelectThemeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectThemeViewModel : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(
        SelectThemeUiState(
            // TODO: 仮で初期値として与えます
            themeChoices = listOf(
                "秋を感じるランチ",
                "休日にじっくり作りたいディナー",
                "きのこのフルコース",
                "サクッと作れる時短レシピ",
                "季節を感じられる和食",
                "特になし",
            ),
        )
    )

    val uiState: StateFlow<SelectThemeUiState> = _uiStateFlow.asStateFlow()

    fun updateInputTheme(inputValue: String) {
        _uiStateFlow.update {
            it.copy(
                selectedTheme = if (inputValue.isBlank()) ThemeRequest.None else ThemeRequest.Request(inputValue)
            )
        }
    }

    class Factory: ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectThemeViewModel() as T
        }
    }
}