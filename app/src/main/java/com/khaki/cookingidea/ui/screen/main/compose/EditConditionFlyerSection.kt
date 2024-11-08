package com.khaki.cookingidea.ui.screen.main.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.R
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun EditConditionFlyerSection(
    selectedImageUrl: String?,
    onClickSelectFlyerButton: () -> Unit,
    onClickFlyerImage: () -> Unit,
    onClickRemoveFlyerButton: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "チラシを参照する",
            style = MaterialTheme.typography.titleMedium,
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
            onClick = onClickSelectFlyerButton,
        ) {
            val buttonTitleRes = if (selectedImageUrl != null) {
                R.string.edit_flyer_image_button_title
            } else {
                R.string.load_flyer_button_title
            }

            Icon(
                imageVector = Icons.Default.Upload,
                contentDescription = null
            )

            Text(
                text = stringResource(id = buttonTitleRes)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewEditConditionFlyer_empty() {
    CookingIdeaTheme {
        EditConditionFlyerSection(
            selectedImageUrl = null,
            onClickSelectFlyerButton = {},
            onClickFlyerImage = {},
            onClickRemoveFlyerButton = {},
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewEditConditionFlyer_image() {
    CookingIdeaTheme {
        EditConditionFlyerSection(
            selectedImageUrl = "",
            onClickSelectFlyerButton = {},
            onClickFlyerImage = {},
            onClickRemoveFlyerButton = {},
        )
    }
}
