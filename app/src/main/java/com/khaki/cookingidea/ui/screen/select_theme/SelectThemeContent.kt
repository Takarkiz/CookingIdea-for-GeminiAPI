package com.khaki.cookingidea.ui.screen.select_theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.ui.screen.select_theme.compose.ThemeSelector
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun SelectThemeContent(
    modifier: Modifier = Modifier,
    selectedThemeTitle: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .padding(
                horizontal = 16.dp,
            )
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "献立を考える上でのテーマを一つ選ぶことができます。\nテーマを決めることで、献立の方向性を決めることができます。\n自由記述も可能です。",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(48.dp))

        ThemeSelector(
            modifier = Modifier.fillMaxWidth(),
            choices = listOf(
                "秋を感じるランチ",
                "休日にじっくり作りたいディナー",
                "きのこのフルコース",
                "サクッと作れる時短レシピ",
                "季節を感じられる和食",
                "特になし",
            ),
            selectedChoice = selectedThemeTitle,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "テーマは",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = selectedThemeTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@PreviewFontScale
@Composable
private fun SelectThemeContentPreview() {
    CookingIdeaTheme {
        SelectThemeContent(
            selectedThemeTitle = "秋を感じるランチ"
        )
    }
}