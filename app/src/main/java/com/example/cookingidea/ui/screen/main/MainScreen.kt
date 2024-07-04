package com.example.cookingidea.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cookingidea.ui.screen.menudialog.MenuDialogContent
import com.example.cookingidea.ui.screen.menudialog.MenuDialogUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainUiState,
    dialogUiState: MenuDialogUiState,
    onClickSelectImage: () -> Unit = {},
    onClickGenerator: () -> Unit = {},
    onDismissDialogRequest: () -> Unit = {},
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "パパッと献立アシスタント")
                }
            )
        },
        content = {
            MainContent(
                modifier = Modifier.padding(it),
                uiState = uiState,
                onClickSelectImage = onClickSelectImage,
                onClickGenerator = onClickGenerator,
            )

            if (uiState.showDialog) {
                ModalBottomSheet(onDismissRequest = onDismissDialogRequest) {
                    MenuDialogContent(
                        uiState = dialogUiState,
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen(
        uiState = MainUiState(),
        dialogUiState = MenuDialogUiState(),
    )
}
