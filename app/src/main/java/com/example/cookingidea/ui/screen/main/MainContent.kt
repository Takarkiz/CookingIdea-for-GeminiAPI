package com.example.cookingidea.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookingidea.R
import com.example.cookingidea.ui.screen.main.compose.EditConditionFlyer
import com.example.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun MainContent(
    uiState: MainUiState,
    onClickSelectImageButton: () -> Unit,
    onClickImage: () -> Unit,
    onClickRemoveImageButton: () -> Unit,
    onClickGenerator: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            EditConditionFlyer(
                selectedImageUrl = uiState.selectedImageUri?.toString(),
                onClickSelectFlyerButton = onClickSelectImageButton,
                onClickFlyerImage = onClickImage,
                onClickRemoveFlyerButton = onClickRemoveImageButton,
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onClickGenerator,
                enabled = uiState.selectedImageUri != null,
            ) {
                Text(text = stringResource(id = R.string.start_create_menu_button_title))
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMainContent() {
    CookingIdeaTheme {
        MainContent(
            uiState = MainUiState(),
            onClickSelectImageButton = {},
            onClickImage = {},
            onClickRemoveImageButton = {},
            onClickGenerator = {},
        )
    }
}
