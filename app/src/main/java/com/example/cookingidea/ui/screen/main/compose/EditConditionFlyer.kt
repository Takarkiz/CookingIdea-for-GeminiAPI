package com.example.cookingidea.ui.screen.main.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookingidea.R
import com.example.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun EditConditionFlyer(
    selectedImageUrl: String?,
    modifier: Modifier = Modifier,
    onClickSelectFlyer: () -> Unit = {},
    onClickFlyerImage: () -> Unit = {},
    onClickRemoveFlyerButton: () -> Unit = {},
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "チラシを参照する",
            style = MaterialTheme.typography.titleSmall
        )

        if (selectedImageUrl != null) {
            FlyerImage(
                selectedImageUrl = selectedImageUrl,
                onClickImage = onClickFlyerImage,
                onClickRemoveButton = onClickRemoveFlyerButton,
            )
        }

        if (selectedImageUrl == null) {
            Spacer(modifier = Modifier.height(4.dp))
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = onClickSelectFlyer,
        ) {
            val buttonTitleRes = if (selectedImageUrl != null) {
                R.string.edit_flyer_image_button_title
            } else {
                R.string.load_flyer_button_title
            }
            Text(text = stringResource(id = buttonTitleRes))
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewEditConditionFlyer_empty() {
    CookingIdeaTheme {
        EditConditionFlyer(
            selectedImageUrl = null
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewEditConditionFlyer_image() {
    CookingIdeaTheme {
        EditConditionFlyer(
            selectedImageUrl = ""
        )
    }
}
