package com.khaki.cookingidea.ui.screen.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.model.Ingredient
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IngredientSelectSection(
    ingredientList: List<Ingredient>,
    enableFlyer: Boolean,
    onClickSelectedIngredient: (Ingredient.ID) -> Unit,
    onClickAddIngredient: () -> Unit,
    onClickAddIngredientFromFlyer: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "利用したい食材",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = "食材一覧",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        min = 80.dp,
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(
                        vertical = 8.dp,
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                ingredientList.forEach { item ->
                    InputChip(
                        selected = true,
                        onClick = {
                            onClickSelectedIngredient(item.id)
                        },
                        label = {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        trailingIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {

                                    },
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    )
                }

                InputChip(
                    selected = false,
                    onClick = onClickAddIngredient,
                    label = {
                        Text(
                            text = "追加する",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .size(16.dp),
                            imageVector = Icons.Default.AddCircleOutline,
                            contentDescription = null
                        )
                    }
                )
            }

            if (enableFlyer) {
                TextButton(
                    onClick = onClickAddIngredientFromFlyer,
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                    )

                    Text(text = "チラシから候補を追加する")
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun IngredientSelectSectionPreview() {
    CookingIdeaTheme {
        IngredientSelectSection(
            ingredientList = listOf(
                Ingredient.of("卵"),
                Ingredient.of("ハイラル米"),
                Ingredient.of("ガッツ人参"),
                Ingredient.of("鎧ダケ"),
                Ingredient.of("ハイラルバス"),
                Ingredient.of("けもの肉"),
            ),
            enableFlyer = true,
            onClickSelectedIngredient = {},
            onClickAddIngredient = {},
            onClickAddIngredientFromFlyer = {},
        )
    }
}
