package com.sample.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.sample.domain.domain_models.MaintainerModel

data class Maintainer(
    @SerializedName("github")
    val github: String? = ""
)

fun Maintainer.asDomain(): MaintainerModel {
    return MaintainerModel(
        github = github ?: ""
    )
}