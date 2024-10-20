package com.khaki.cookingidea.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.model.Ingredient
import com.khaki.cookingidea.ui.screen.main.compose.EditConditionFlyerSection
import com.khaki.cookingidea.ui.screen.main.compose.IngredientSelectSection
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun MainContent(
    uiState: MainUiState,
    onClickSelectImageButton: () -> Unit,
    onClickImage: () -> Unit,
    onClickRemoveImageButton: () -> Unit,
    onClickGenerator: () -> Unit,
    onClickIngredient: (Ingredient.ID) -> Unit,
    onClickAddIngredient: () -> Unit,
    onClickAddIngredientFromFlyer: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            EditConditionFlyerSection(
                selectedImageUrl = uiState.selectedImageUri?.toString(),
                onClickSelectFlyerButton = onClickSelectImageButton,
                onClickFlyerImage = onClickImage,
                onClickRemoveFlyerButton = onClickRemoveImageButton,
            )

            IngredientSelectSection(
                ingredientList = uiState.ingredientList,
                enableFlyer = uiState.selectedImageUri != null,
                onClickSelectedIngredient = {},
                onClickAddIngredient = {},
                onClickAddIngredientFromFlyer = {},
            )

        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMainContent() {
    CookingIdeaTheme {
        MainContent(
            uiState = MainUiState(),
            onClickSelectImageButton = {},
            onClickImage = {},
            onClickRemoveImageButton = {},
            onClickGenerator = {},
            onClickIngredient = {},
            onClickAddIngredient = {},
            onClickAddIngredientFromFlyer = {},
        )
    }
}
