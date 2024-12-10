package com.khaki.cookingidea.ui.screen.flyer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khaki.cookingidea.model.MenuRequestParam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlyerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlyerUiState())
    val uiState: StateFlow<FlyerUiState> = _uiState.asStateFlow()

    fun setInitialRequestParam(requestParam: MenuRequestParam) {
        _uiState.update {
            it.copy(requestParam = requestParam)
        }
    }

    fun selectFlyerImage(imageUri: String) {
        _uiState.update {
            it.copy(requestParam = it.requestParam.newBuilder().setUseFlyer(imageUri).build())
        }
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FlyerViewModel() as T
        }
    }
}
