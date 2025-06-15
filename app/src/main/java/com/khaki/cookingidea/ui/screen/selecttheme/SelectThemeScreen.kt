package com.khaki.cookingidea.ui.screen.selecttheme

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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.khaki.cookingidea.ui.screen.selecttheme.compose.SelectThemeUiState
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectThemeScreen(
    uiState: SelectThemeUiState,
    onUpdateSelectedTheme: (String) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit = {},
) {

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
                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            Button(
                onClick = onNext
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
            choices = uiState.themeChoices,
            selectedThemeTitle = uiState.selectedTheme,
            onUpdateTheme = { themeTitle ->
                onUpdateSelectedTheme(themeTitle)
            }
        )
    }
}

@PreviewScreenSizes
@Composable
private fun SelectThemeScreenPreview() {
    CookingIdeaTheme {
        SelectThemeScreen(
            uiState = SelectThemeUiState(
                themeChoices = listOf(
                    "秋を感じるランチ",
                    "休日にじっくり作りたいディナー",
                    "きのこのフルコース",
                    "サクッと作れる時短レシピ",
                    "季節を感じられる和食",
                    "特になし",
                ),
                selectedTheme = ThemeRequest.Request("季節を感じられる和食")
            ),
            onUpdateSelectedTheme = {},
            onBack = {},
            onNext = {},
        )
    }
}
