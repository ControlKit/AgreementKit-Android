package com.sepanta.controlkit.agreementkit.service

import com.sepanta.controlkit.agreementkit.service.apiError.NetworkResult
import com.sepanta.controlkit.agreementkit.service.apiError.handleApi
import com.sepanta.controlkit.agreementkit.service.model.ActionResponse
import com.sepanta.controlkit.agreementkit.service.model.ApiDataResponse


class AgreementApi(private val apiService: ApiService) {

    suspend fun getData(
        route: String,
        appId: String,
        version: String,
        deviceId: String,
        sdkVersion: String,
        name: String,
    ): NetworkResult<ApiDataResponse> {
        return handleApi {
            apiService.getData(
                route,
                appId = appId,
                version = version,
                deviceId = deviceId,
                sdkVersion = sdkVersion,
                name
            )
        }
    }

    suspend fun setAction(
        route: String,
        appId: String,
        version: String,
        deviceId: String,
        sdkVersion: String,
        action: String,
    ): NetworkResult<ActionResponse> {
        return handleApi {
            apiService.setAction(
                url = route,
                appId = appId,
                version = version,
                deviceId = deviceId,
                sdkVersion = sdkVersion,
                action = action,
            )
        }
    }
}
