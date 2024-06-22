package com.example.cookingidea.ui.screen.main

import android.net.Uri

data class MainUiState(
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
)