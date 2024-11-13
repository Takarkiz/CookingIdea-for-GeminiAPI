package com.khaki.cookingidea.ui.screen.select_theme

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.ui.screen.select_theme.compose.ThemeSelector
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun SelectThemeContent(
    modifier: Modifier = Modifier,
    choices: List<String>,
    selectedThemeTitle: ThemeRequest,
    onUpdateTheme: (String) -> Unit,
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
            choices = choices,
            selectedChoice = selectedThemeTitle,
            onSelectTheme = {
                onUpdateTheme(it)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "テーマは",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = when (selectedThemeTitle) {
                is ThemeRequest.None -> ""
                is ThemeRequest.Request -> selectedThemeTitle.value
            },
            singleLine = true,
            placeholder = {
                if (selectedThemeTitle is ThemeRequest.None) {
                    Text(
                        text = "特になし",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            },
            textStyle = MaterialTheme.typography.titleLarge,
            onValueChange = { input ->
                onUpdateTheme(input)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        onUpdateTheme("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}

@PreviewFontScale
@Composable
private fun SelectThemeContentPreview() {
    CookingIdeaTheme {
        SelectThemeContent(
            choices = listOf(
                "秋を感じるランチ",
                "休日にじっくり作りたいディナー",
                "きのこのフルコース",
                "サクッと作れる時短レシピ",
                "季節を感じられる和食",
                "特になし",
            ),
            selectedThemeTitle = ThemeRequest.Request("秋を感じるランチ"),
            onUpdateTheme = {},
        )
    }
}