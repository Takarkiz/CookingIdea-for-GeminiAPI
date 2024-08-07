package com.example.cookingidea.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cookingidea.R
import com.example.cookingidea.ui.screen.menudialog.MenuDialogContent
import com.example.cookingidea.ui.screen.menudialog.MenuDialogUiState
import com.example.cookingidea.ui.theme.CookingIdeaTheme

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
            LargeTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.menu_param_edit_screen_title))
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
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
    CookingIdeaTheme {
        MainScreen(
            uiState = MainUiState(),
            dialogUiState = MenuDialogUiState(),
        )
    }
}
