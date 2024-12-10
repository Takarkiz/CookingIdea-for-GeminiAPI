package com.khaki.cookingidea.ui.screen.flyer.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.ui.screen.main.compose.FlyerImage
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun FlyerContent(
    flyerUri: String?,
    onSelectUploadingButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "アップロードしたチラシを元に、献立を作成することができます。\nチラシを選択せずに、献立を作成することもできます。",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {

            if (flyerUri != null) {
                FlyerImage(
                    selectedImageUrl = flyerUri,
                    onClickImage = {},
                    onClickRemoveButton = {},
                )
            }
        }

        ElevatedButton(
            onClick = onSelectUploadingButton,
        ) {
            Text(
                text = if (flyerUri == null) "チラシをアップロードする" else "チラシを変更する",
            )
        }

        Button(
            onClick = onSelectUploadingButton,
        ) {
            Text(text = if (flyerUri == null) "チラシを利用しない" else "チラシ選択を確定")
        }
    }
}

@Preview
@Composable
private fun FlyerContentPreview_null() {
    CookingIdeaTheme {
        FlyerContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                ),
            flyerUri = null,
            onSelectUploadingButton = {},
        )
    }
}

@Preview
@Composable
private fun FlyerContentPreview_content() {
    CookingIdeaTheme {
        FlyerContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                ),
            flyerUri = "hoge",
            onSelectUploadingButton = {},
        )
    }
}