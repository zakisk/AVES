package com.aves.ui.photo_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aves.R
import com.aves.ui.common_components.AppDialog
import com.aves.ui.common_components.DialogType
import com.aves.ui.common_components.Loading
import com.aves.ui.common_components.RemoteImage
import com.aves.ui.theme.LocalSpacing


@Composable
fun PhotoDetailScreen(
    navController: NavHostController,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = spacing.XXL)
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(
                        start = spacing.large,
                        top = spacing.small,
                        bottom = spacing.small
                    )
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { navController.popBackStack() }
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            val imageUrl =
                state.photo.urls?.full ?: state.photo.urls?.raw ?: state.photo.urls?.regular
            val description = state.photo.description ?: state.photo.altDescription
            RemoteImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp, 250.dp),
                imageLink = imageUrl ?: "",
                error = R.drawable.ic_placeholder_image,
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = (description ?: "").ifBlank { "Description is not available" },
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(spacing.medium)
            )
        }
    }

    if (state.isLoading) {
        Loading()
    }

    if (viewModel.isShowDialog) {
        AppDialog(
            message = state.error ?: stringResource(id = R.string.something_went_wrong),
            type = DialogType.ERROR,
            tint = Color.Red.copy(alpha = .2F),
            onConfirm = { viewModel.isShowDialog = false }
        )
    }
}