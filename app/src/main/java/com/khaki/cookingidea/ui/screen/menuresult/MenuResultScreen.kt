package com.khaki.cookingidea.ui.screen.menuresult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khaki.cookingidea.R
import com.khaki.cookingidea.ui.screen.menuresult.MenuResultUiState
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuResultScreen(
    uiState: MenuResultUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.menu_dialog_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Title with decorative elements
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.menu_dialog_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(bottom = 8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        thickness = 1.dp
                    )
                }
            }

            // Content card with improved styling
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    text = formatMenuTextToAnnotatedString(uiState.generatedText),
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2f,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

/**
 * Formats the menu text to an AnnotatedString with styled sections
 */
@Composable
private fun formatMenuTextToAnnotatedString(text: String): AnnotatedString {
    // If the text is empty, return empty AnnotatedString
    if (text.isBlank()) return AnnotatedString("")

    // Get the primary color from the theme
    val primaryColor = MaterialTheme.colorScheme.primary

    // Normalize line breaks and ensure proper spacing
    var formattedText = text.replace("\n\n\n", "\n\n")

    // Enhance section headers (text in Japanese brackets)
    formattedText = formattedText.replace(
        regex = Regex("【([^】]*)】"),
        replacement = "\n【$1】\n"
    ).trim()

    // Remove any duplicate line breaks that might have been created
    while (formattedText.contains("\n\n\n")) {
        formattedText = formattedText.replace("\n\n\n", "\n\n")
    }

    // Create an AnnotatedString with styled sections
    return buildAnnotatedString {
        val lines = formattedText.split("\n")

        for (i in lines.indices) {
            val line = lines[i]

            // Check if this line is a section header (contains text in Japanese brackets)
            if (line.matches(Regex(".*【([^】]*)】.*"))) {
                // Add extra spacing before section headers (except for the first one)
                if (i > 0 && i < lines.size - 1 && lines[i-1].isNotBlank()) {
                    append("\n")
                }

                // Apply special styling to section headers
                withStyle(
                    SpanStyle(
                        color = primaryColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        background = primaryColor.copy(alpha = 0.1f)
                    )
                ) {
                    append(line)
                }

                // Add extra spacing after section headers
                if (i < lines.size - 1 && lines[i+1].isNotBlank()) {
                    append("\n")
                }
            } else {
                // Regular text
                append(line)
            }

            // Add newline if not the last line
            if (i < lines.size - 1) {
                append("\n")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMenuResultScreen() {
    CookingIdeaTheme {
        MenuResultScreen(
            uiState = MenuResultUiState(
                generatedText = """
                    今日の献立

                    【メインディッシュ】
                    鮭のムニエル レモンバターソース

                    【副菜】
                    ほうれん草のごま和え
                    きのこのマリネ

                    【汁物】
                    豆腐と若布のお味噌汁

                    【ご飯もの】
                    雑穀ご飯

                    【デザート】
                    季節のフルーツ

                    【使用した食材】
                    鮭、レモン、バター、ほうれん草、ごま、しめじ、えのき、マッシュルーム、豆腐、若布、だし、味噌、雑穀米、りんご、みかん
                """.trimIndent()
            ),
            onBackClick = {}
        )
    }
}
