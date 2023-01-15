package com.aves.domain.models.global

import com.aves.domain.models.common.Links
import com.aves.domain.models.common.Urls
import com.aves.domain.models.sponsorship.Sponsorship
import com.aves.domain.models.topic_submissions.TopicSubmissions
import com.aves.domain.models.user.User
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") var id: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("promoted_at") var promotedAt: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("blur_hash") var blurHash: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("alt_description") var altDescription: String? = null,
    @SerializedName("urls") var urls: Urls? = null,
    @SerializedName("links") var links: Links? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("current_user_collections") var currentUserCollections: ArrayList<String>? = null,
    @SerializedName("sponsorship") var sponsorship: Sponsorship? = Sponsorship(),
    @SerializedName("topic_submissions") var topicSubmissions: TopicSubmissions? = null,
    @SerializedName("user") var user: User? = null

)
