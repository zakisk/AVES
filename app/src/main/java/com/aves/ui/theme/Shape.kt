package com.aves.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

data class CustomShapes (
    val smallShape : CornerBasedShape = RoundedCornerShape(4.dp),
    val mediumShape : CornerBasedShape = RoundedCornerShape(8.dp),
    val largeShape : CornerBasedShape = RoundedCornerShape(16.dp),
    val mediumCardShape: CornerBasedShape = RoundedCornerShape(24.dp),
    val largeCardShape: CornerBasedShape = RoundedCornerShape(32.dp)
)

val LocalCustomShapes = compositionLocalOf { CustomShapes() }