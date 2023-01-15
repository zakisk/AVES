package com.aves.ui.photo_detail_screen

import com.aves.domain.models.global.Photo

data class PhotoDetailScreenState(
    val isLoading: Boolean = false,
    val photo: Photo = Photo(),
    val error: String? = null
)
