package com.example.cookingidea

import android.net.Uri

data class MainUiState(
    val selectedImageUri: Uri? = null,
    val generatedText: String? = null,
    val isLoading: Boolean = false,
)