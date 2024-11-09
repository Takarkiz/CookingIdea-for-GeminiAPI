package com.khaki.cookingidea.ui.screen.select_theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectThemeScreen() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "テーマの選択"
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = {

                }
            ) {
                Text(text = "次に進む")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        SelectThemeContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(it),
            selectedThemeTitle = "特になし"
        )
    }
}

@PreviewScreenSizes
@Composable
private fun SelectThemeScreenPreview() {
    CookingIdeaTheme {
        SelectThemeScreen()
    }
}