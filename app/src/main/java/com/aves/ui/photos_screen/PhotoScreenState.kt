package com.aves.ui.photos_screen

import com.aves.domain.models.global.Photo

data class PhotoScreenState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null
)
