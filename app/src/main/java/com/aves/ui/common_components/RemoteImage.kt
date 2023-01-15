package com.aves.ui.common_components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.aves.R


@Composable
fun RemoteImage(
    modifier: Modifier,
    imageLink: String,
    contentScale: ContentScale = ContentScale.Fit,
    @DrawableRes error: Int = R.drawable.ic_avatar,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
) {
    AsyncImage(
        modifier = modifier,
        model = imageLink,
        contentDescription = null,
        contentScale = contentScale,
        error = painterResource(id = error),
        onError = onError,
        onLoading = {  }
    )
}