package com.khaki.cookingidea.ui.screen.selecttheme.compose

import com.khaki.cookingidea.ui.screen.selecttheme.ThemeRequest

data class SelectThemeUiState(
    val themeChoices: List<String> = emptyList(),
    val selectedTheme: ThemeRequest = ThemeRequest.None,
)
