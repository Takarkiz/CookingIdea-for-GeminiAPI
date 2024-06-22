package com.example.cookingidea.ui.screen.menudialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MenuDialogContent(
    modifier: Modifier = Modifier,
    menuText: String,
    uiState: MenuDialogUiState,
) {

    Column {
        Text(
            text = "今日の献立",
            style = MaterialTheme.typography.headlineMedium,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        )
        {
            Text(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                text = uiState.generatedText ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}