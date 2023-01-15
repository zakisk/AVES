package com.aves.domain.models.topic_submissions

import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("street-photography") var streetPhotography: StreetPhotography? = null,
    @SerializedName("film") var film: Film? = null
)
