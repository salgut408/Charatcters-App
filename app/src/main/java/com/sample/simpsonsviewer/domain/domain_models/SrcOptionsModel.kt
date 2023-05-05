package com.sample.simpsonsviewer.domain.domain_models


import com.google.gson.annotations.SerializedName

data class SrcOptionsModel(
    val directory: String = "",
    val isFanon: Int = 0,
    val isMediawiki: Int = 0,
    val isWikipedia: Int = 0,
    val language: String = "",
    val minAbstractLength: String = "",
    val skipAbstract: Int = 0,
    val skipAbstractParen: Int = 0,
    val skipEnd: String = "",
    val skipIcon: Int = 0,
    val skipImageName: Int = 0,
    val skipQr: String = "",
    val sourceSkip: String = "",
    val srcInfo: String = ""
)