package com.example.cookingidea.ui.screen.menudialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuDialogContent(
    modifier: Modifier = Modifier,
    uiState: MenuDialogUiState,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
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
            text = uiState.generatedText,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}