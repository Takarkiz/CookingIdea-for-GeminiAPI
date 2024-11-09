package com.khaki.cookingidea.ui.screen.select_theme

sealed interface ThemeRequest {

    data object None: ThemeRequest

    data class Request(
        val value: String
    ): ThemeRequest
}