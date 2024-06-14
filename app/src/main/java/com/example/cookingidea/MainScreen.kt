package com.example.cookingidea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoaderFactory
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainUiState,
    onClickSelectImage: () -> Unit = {},
    onClickGenerator: () -> Unit = {},
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
        }
    )

}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onClickSelectImage: () -> Unit = {},
    onClickGenerator: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            model = uiState.selectedImageUri,
            contentDescription = "selected_image",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.FillHeight
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

        Text(
            text = "今日の献立",
            style = MaterialTheme.typography.headlineMedium,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .verticalScroll(rememberScrollState()),
            text = uiState.generatedText ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        uiState = MainUiState(
            generatedText = "Generated Text",
        ),
    )
}