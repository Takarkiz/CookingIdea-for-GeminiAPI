package com.khaki.cookingidea.ui.screen.selecttheme.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.ui.screen.selecttheme.ThemeRequest
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun ThemeSelector(
    choices: List<String>,
    selectedChoice: ThemeRequest,
    onSelectTheme: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "献立のテーマ候補",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            choices.forEach {

                val selectedModifier = when (selectedChoice) {
                    is ThemeRequest.None -> {
                        if (it == "特になし") {
                            Modifier.background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(20.dp)
                            )
                        } else Modifier
                    }

                    is ThemeRequest.Request -> {
                        if (it == selectedChoice.value) {
                            Modifier.background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(20.dp)
                            )
                        } else {
                            Modifier
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .then(selectedModifier)
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = ripple(),
                            onClick = {
                                onSelectTheme(it)
                            },
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 32.dp,
                        ),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ThemeSelectorPreview() {
    CookingIdeaTheme {
        ThemeSelector(
            choices = listOf(
                "秋を感じるランチ",
                "休日にじっくり作りたいディナー",
                "きのこのフルコース",
                "サクッと作れる時短レシピ",
                "季節を感じられる和食",
                "特になし",
            ),
            selectedChoice = ThemeRequest.None,
            onSelectTheme = {},
        )
    }
}
