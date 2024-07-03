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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onClickSelectImage: () -> Unit = {},
    onClickGenerator: () -> Unit = {},
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(32.dp),
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AsyncImage(
                model = uiState.selectedImageUri,
                contentDescription = "selected_image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Fit
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onClickSelectImage,
            ) {
                Text(text = "チラシを読み込む")
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onClickGenerator,
                enabled = uiState.selectedImageUri != null,
            ) {
                Text(text = "献立を生成する")
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
