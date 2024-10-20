package com.khaki.cookingidea.ui.screen.main

import android.net.Uri
import com.khaki.cookingidea.model.Ingredient

data class MainUiState(
    val selectedImageUri: Uri? = null,
    val ingredientList: List<Ingredient> = listOf(),
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
)
