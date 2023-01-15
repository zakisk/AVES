package com.aves.domain.models.topic_submissions

import com.google.gson.annotations.SerializedName

data class StreetPhotography(
    @SerializedName("status") var status: String? = null
)
