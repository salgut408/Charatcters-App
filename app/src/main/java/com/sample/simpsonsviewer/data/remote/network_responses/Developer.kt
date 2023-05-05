package com.sample.simpsonsviewer.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.sample.simpsonsviewer.domain.domain_models.DeveloperModel

data class Developer(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = ""
)

fun Developer.asDomain(): DeveloperModel {
    return DeveloperModel(
        name = name ?: "",
        type = type ?: "",
        url = url ?: ""
    )
}