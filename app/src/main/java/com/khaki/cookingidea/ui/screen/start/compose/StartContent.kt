package com.khaki.cookingidea.ui.screen.start.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.cookingidea.R
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme

@Composable
fun StartContent(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .padding(
                top = 64.dp,
                start = 32.dp,
                end = 32.dp,
                bottom = 64.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "献立決まるくん",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(
                    shape = RoundedCornerShape(12.dp)
                ),
            painter = painterResource(id = R.drawable.cooking_idea_top_image),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "このアプリでは、AIの技術を利用して指定した食材、チラシなどから、おすすめの献立をサジェストします。",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        ElevatedButton(
            onClick = onClickStart
        ) {
            Text(
                text = "使ってみる"
            )
        }
    }
}

@Preview
@Composable
fun StartContentPreview() {

    CookingIdeaTheme {
        StartContent(
            onClickStart = {}
        )
    }
}
