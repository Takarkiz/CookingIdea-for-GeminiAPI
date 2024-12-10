package com.khaki.cookingidea.ui.screen.flyer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khaki.cookingidea.model.MenuRequestParam
import com.khaki.cookingidea.ui.screen.flyer.compose.FlyerContent
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlyerScreen(
    uiState: FlyerUiState,
    onSelectUploadingButton: () -> Unit,
    onBack: () -> Unit,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "チラシの選択"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) {
        FlyerContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(it),
            flyerUri = uiState.requestParam.flyerUri,
            onSelectUploadingButton = onSelectUploadingButton,
        )
    }
}

@Preview
@Composable
private fun FlyerScreenPreview() {
    CookingIdeaTheme {
        FlyerScreen(
            uiState = FlyerUiState(
                requestParam = MenuRequestParam.Builder().build()
            ),
            onSelectUploadingButton = {},
            onBack = {},
        )
    }
}
