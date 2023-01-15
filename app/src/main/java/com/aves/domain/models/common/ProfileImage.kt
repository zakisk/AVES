package com.aves.domain.models.common

import com.google.gson.annotations.SerializedName

data class ProfileImage(
    @SerializedName("small") var small: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("large") var large: String? = null
)
