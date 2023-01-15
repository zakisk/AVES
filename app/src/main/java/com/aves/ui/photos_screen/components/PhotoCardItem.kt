package com.aves.ui.photos_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aves.R
import com.aves.ui.common_components.RemoteImage
import com.aves.ui.theme.LocalCustomShapes
import com.aves.ui.theme.LocalSpacing


@Composable
fun PhotoCardItem(
    imageUrl: String?,
    description: String?,
    profileImgUrl: String?,
    userName: String?,
    onClick: () -> Unit,
    onClickProfile: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shape = LocalCustomShapes.current.mediumShape
    val width = (LocalConfiguration.current.screenWidthDp - 32).dp
    Column(
        modifier = Modifier
            .padding(horizontal = spacing.medium, vertical = spacing.small)
            .fillMaxWidth()
            .border(width = .3.dp, color = Color.Gray, shape = shape)
            .clip(shape)
    ) {
        RemoteImage(
            modifier = Modifier
                .width(width)
                .heightIn(200.dp, 250.dp)
                .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .clickable(onClick = onClick),
            imageLink = imageUrl ?: "",
            error = R.drawable.ic_placeholder_image,
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            text = (description ?: "").ifBlank { "Description is not available" },
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(spacing.small)
        )

        Row(
            modifier = Modifier
                .padding(spacing.small)
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource(),
                    onClick = onClickProfile
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                modifier = Modifier
                    .padding(spacing.small)
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(width = .3.dp, color = Color.LightGray, shape = CircleShape),
                imageLink = profileImgUrl ?: ""
            )

            Text(
                text = userName ?: "",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                ),
                modifier = Modifier.padding(spacing.small)
            )
        }

        Spacer(modifier = Modifier.height(spacing.small))
    }
}

