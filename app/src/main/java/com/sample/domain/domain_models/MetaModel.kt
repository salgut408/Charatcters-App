package com.sample.domain.domain_models


data class MetaModel(
    val attribution: Any = Any(),
    val blockgroup: Any = Any(),
    val createdDate: Any = Any(),
    val description: String = "",
    val designer: Any = Any(),
    val devDate: Any = Any(),
    val devMilestone: String = "",
    val developer: List<DeveloperModel>? = listOf(),
    val exampleQuery: String = "",
    val id: String = "",
    val isStackexchange: Any = Any(),
    val jsCallbackName: String = "",
    val liveDate: Any = Any(),
    val maintainer: MaintainerModel = MaintainerModel(),
    val name: String = "",
    val perlModule: String = "",
    val producer: Any = Any(),
    val productionState: String = "",
    val repo: String = "",
    val signalFrom: String = "",
    val srcDomain: String = "",
    val srcId: Int = 0,
    val srcName: String = "",
    val srcOptions: SrcOptionsModel = SrcOptionsModel(),
    val srcUrl: Any = Any(),
    val status: String = "",
    val tab: String = "",
    val topic: List<String> = listOf(),
    val unsafe: Int = 0
)