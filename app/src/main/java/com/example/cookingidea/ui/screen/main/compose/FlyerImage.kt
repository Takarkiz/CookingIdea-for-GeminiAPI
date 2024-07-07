package com.example.cookingidea.ui.screen.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun FlyerImage(
    selectedImageUrl: String,
    onClickImage: () -> Unit,
    onClickRemoveButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            model = selectedImageUrl,
            contentDescription = "selected_image",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onClickImage()
                },
            contentScale = ContentScale.Fit
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
            onClick = { onClickRemoveButton() }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "remove_flyer_image",
            )
        }
    }
}

@Preview
@Composable
fun PreviewFlyerImage() {
    CookingIdeaTheme {
        FlyerImage(
            selectedImageUrl = "",
            onClickImage = {},
            onClickRemoveButton = {}
        )
    }
}
