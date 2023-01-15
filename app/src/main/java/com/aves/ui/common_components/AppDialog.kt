package com.aves.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.aves.R
import com.aves.ui.common_components.DialogType.ERROR
import com.aves.ui.theme.LincolnGreen
import com.aves.ui.theme.LocalCustomShapes
import com.aves.ui.theme.LocalSpacing


@Composable
fun AppDialog(
    message: String,
    type: DialogType,
    tint: Color,
    onConfirm: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shape: Shape = LocalCustomShapes.current.mediumShape
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = shape),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing.medium))

            Icon(
                imageVector = if (type == ERROR) Icons.Default.Error else Icons.Default.Done,
                contentDescription = null,
                tint = if (type == ERROR) Color.Red else Color.LincolnGreen,
                modifier = Modifier
                    .padding(horizontal = spacing.medium, vertical = spacing.small)
                    .size(48.dp)
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (type == ERROR) Color.Red else Color.LincolnGreen
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = spacing.medium, vertical = spacing.small)
                    .fillMaxWidth()
            )

            Divider(thickness = .5.dp, color = tint)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    modifier = Modifier.padding(spacing.small),
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (type == ERROR) Color.Red else Color.LincolnGreen,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}