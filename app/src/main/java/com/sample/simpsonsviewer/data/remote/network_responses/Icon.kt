package com.sample.simpsonsviewer.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.sample.simpsonsviewer.domain.domain_models.IconModel

data class Icon(
    @SerializedName("Height")
    val height: String? = "0",
    @SerializedName("URL")
    val urL: String? = "",
    @SerializedName("Width")
    val width: String? = "0"
)
fun Icon.asDomain(): IconModel {
    return IconModel(
        height = height ?: "0" ,
        urL = urL ?: "",
        width = width ?: "0"
    )
}