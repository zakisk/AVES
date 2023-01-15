package com.aves.ui.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.aves.ui.common_components.RemoteImage
import com.aves.ui.photo_detail_screen.PhotoDetailViewModel
import com.aves.ui.theme.LocalSpacing


@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            modifier = Modifier
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

            Text(
                text = stringResource(id = R.string.profile),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(spacing.medium)
                    .fillMaxWidth(.8F)
            )
        }

        Divider(color = Color.LightGray.copy(alpha = .3F), thickness = .3.dp)
        Spacer(modifier = Modifier.height(spacing.medium))

        RemoteImage(
            modifier = Modifier
                .padding(spacing.small)
                .size(120.dp)
                .clip(CircleShape)
                .border(width = .3.dp, color = Color.LightGray, shape = CircleShape),
            imageLink = state.photo.user?.profileImage?.large ?: ""
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = state.photo.user?.name ?: state.photo.user?.firstName ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(spacing.small)
        )


        Text(
            text = (state.photo.user?.location ?: "").ifBlank { "Unknown Location" },
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            modifier = Modifier.padding(spacing.small)
        )

        Text(
            text = state.photo.user?.bio ?: "",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = spacing.small, horizontal = spacing.large)
        )
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