package com.khaki.cookingidea.ui.screen.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun FlyerImage(
    selectedImageUrl: String,
    onClickImage: () -> Unit,
    onClickRemoveButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
    ) {
        AsyncImage(
            model = selectedImageUrl,
            contentDescription = "selected_image",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .clickable(
                    onClick = {
                        onClickImage()
                    },
                    onClickLabel = "open_flyer_preview"
                ),
            contentScale = ContentScale.Fit,
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
            onClick = { onClickRemoveButton() },
        ) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Default.Close,
                contentDescription = "remove_flyer_image",
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFlyerImage() {
    CookingIdeaTheme {
        FlyerImage(
            selectedImageUrl = "",
            onClickImage = {},
            onClickRemoveButton = {}
        )
    }
}
