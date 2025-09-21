package com.sepanta.controlkit.agreementkit.service.model

import com.sepanta.controlkit.agreementkit.utils.Utils.getContentBySystemLang
import java.util.Locale
import kotlin.collections.firstOrNull

data class ApiDataResponse(
    val data: ApiData
)

data class ApiData(
    val id: String?,
    val name: String?,
    val title: List<LocalizedText>?,
    val description: List<LocalizedText>?,
    val force: Boolean?,
    val icon: String?,
    val link: String?,
    val agreement_title: List<LocalizedText>?,
    val decline_button_title: List<LocalizedText>?,
    val accept_button_title: List<LocalizedText>?,
    val version: Int?,
    val sdk_version: Int?,
    val minimum_version: String?,
    val maximum_version: String?,
    val created_at: String?
)

data class LocalizedText(
    val language: String?,
    val content: String?
)


fun ApiDataResponse.toDomain(lang: String?): DataResponse {
    val d = this.data
    return DataResponse(
        id = d.id,
        version = 1,
        title = d.title.getContentBySystemLang(lang),
        description = d.description.getContentBySystemLang(lang),
        sdkVersion = d.sdk_version?.toString(),
        createdAt = d.created_at,
        buttonTitle = d.accept_button_title.getContentBySystemLang(lang),
        declineButtonTitle = d.decline_button_title.getContentBySystemLang(lang),
        agreementTitle = d.agreement_title.getContentBySystemLang(lang),

    )
}

