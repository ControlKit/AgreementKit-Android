package com.sepanta.controlkit.agreementkit.service.model

data class
DataResponse(
    val id: String?=null,
    val version: Int?=null,
    val title: String?=null,
    val agreementTitle: String?=null,
    val description: String?=null,
    val buttonTitle: String?=null,
    val declineButtonTitle: String?=null,
    val sdkVersion: String?=null,
    val createdAt: String?=null
)