package com.aves.ui.theme

import androidx.compose.runtime.compositionLocalOf
import com.aves.domain.models.user.User


val LocalUser = compositionLocalOf { User() }