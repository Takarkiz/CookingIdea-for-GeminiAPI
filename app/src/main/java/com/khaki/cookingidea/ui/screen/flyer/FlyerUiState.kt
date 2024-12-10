package com.khaki.cookingidea.ui.screen.flyer

import androidx.compose.runtime.Stable
import com.khaki.cookingidea.model.MenuRequestParam

@Stable
data class FlyerUiState(
    val requestParam: MenuRequestParam = MenuRequestParam.Builder().build(),
)
