package com.aves.ui.photos_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aves.R
import com.aves.ui.common_components.AppDialog
import com.aves.ui.common_components.DialogType
import com.aves.ui.common_components.Loading
import com.aves.ui.navigation.Screen
import com.aves.ui.photos_screen.components.PhotoCardItem
import com.aves.ui.theme.Calypso
import com.aves.ui.theme.LocalSpacing


@Composable
fun PhotosScreen(navController: NavHostController, viewModel: PhotosViewModel = hiltViewModel()) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsState()
    LazyColumn {

        item {
            Text(
                text = stringResource(id = R.string.photos),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Calypso,
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = spacing.XXL, bottom = spacing.large)
                    .fillMaxWidth()
            )

            Divider(color = Color.LightGray, thickness = .5.dp)
        }

        items(state.photos) { photo ->
            val imageUrl = photo.urls?.full ?: photo.urls?.raw ?: photo.urls?.regular
            val description = photo.description ?: photo.altDescription
            PhotoCardItem(
                imageUrl = imageUrl,
                description = description,
                profileImgUrl = photo.user?.profileImage?.large ?: photo.user?.profileImage?.medium,
                userName = photo.user?.name ?: "${photo.user?.firstName} ${photo.user?.lastName}",
                onClick = {
                    navController.navigate("${Screen.PhotoDetailScreen.route}/${photo.id}")
                },
                onClickProfile = {
                    navController.navigate("${Screen.ProfileScreen.route}/${photo.id}")
                }
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