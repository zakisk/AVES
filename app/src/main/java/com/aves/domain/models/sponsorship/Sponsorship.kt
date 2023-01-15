package com.aves.domain.models.sponsorship

import com.google.gson.annotations.SerializedName

data class Sponsorship(
    @SerializedName("impression_urls") var impressionUrls: ArrayList<String>? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("tagline_url") var taglineUrl: String? = null,
    @SerializedName("sponsor") var sponsor: Sponsor? = null
)
