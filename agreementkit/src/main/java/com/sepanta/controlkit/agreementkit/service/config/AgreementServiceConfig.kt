package com.sepanta.controlkit.agreementkit.service.config

import com.sepanta.controlkit.agreementkit.view.config.AgreementViewConfig


data class AgreementServiceConfig(
    var viewConfig: AgreementViewConfig = AgreementViewConfig(),
    var version: String ,
    var name: String ,
    var appId: String ,
    var lang: String= "en",
    var deviceId: String?=null,
    var timeOut: Long = 5000L,
    var timeRetryThreadSleep: Long = 1000L,
    var maxRetry: Int = 5
    )