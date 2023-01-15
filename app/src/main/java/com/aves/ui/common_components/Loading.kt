package com.aves.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aves.ui.theme.Calypso
import com.aves.ui.theme.LocalCustomShapes
import com.aves.ui.theme.VioletWeb


@Composable
fun Loading(backgroundColor: Color = Color.White, indicatorColor: Color = Color.Calypso) {
    val shape = LocalCustomShapes.current.mediumShape
    Dialog(onDismissRequest = { }) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(color = backgroundColor, shape = shape),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = indicatorColor, strokeWidth = 3.dp)
        }
    }
}