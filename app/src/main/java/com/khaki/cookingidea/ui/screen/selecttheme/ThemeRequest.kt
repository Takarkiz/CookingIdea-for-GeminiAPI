package com.khaki.cookingidea.ui.screen.selecttheme

sealed interface ThemeRequest {

    data object None : ThemeRequest

    data class Request(
        val value: String,
    ) : ThemeRequest
}
