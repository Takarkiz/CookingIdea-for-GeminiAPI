package com.khaki.cookingidea.ui.screen.select_theme.compose

import com.khaki.cookingidea.ui.screen.select_theme.ThemeRequest

data class SelectThemeUiState(
    val themeChoices: List<String> = emptyList(),
    val selectedTheme: ThemeRequest = ThemeRequest.None,
)