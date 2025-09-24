package com.sepanta.controlkit.agreementkit.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sepanta.controlkit.agreementkit.BuildConfig
import com.sepanta.controlkit.agreementkit.service.AgreementApi
import com.sepanta.controlkit.agreementkit.service.apiError.NetworkResult
import com.sepanta.controlkit.agreementkit.service.config.AgreementServiceConfig
import com.sepanta.controlkit.agreementkit.service.model.toDomain
import com.sepanta.controlkit.agreementkit.view.viewmodel.state.AgreementState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AgreementViewModel(
    private val api: AgreementApi,
) : ViewModel() {
    private val url = BuildConfig.API_URL

    private var config: AgreementServiceConfig? = null
    fun setConfig(config: AgreementServiceConfig) {
        this.config = config
    }

    private var itemId: String? = null

    private val _mutableState = MutableStateFlow<AgreementState>(AgreementState.Initial)
    val state: StateFlow<AgreementState> = _mutableState.asStateFlow()

    fun clearState() {
        _mutableState.value = AgreementState.Initial
    }

    fun sendAction(action: String) {

        if (itemId == null) return
        viewModelScope.launch {
            val data = api.setAction(
                url + "/${itemId}",
                config!!.appId,
                config!!.version,
                config!!.deviceId ?: "",
                BuildConfig.LIB_VERSION_NAME,
                action,
            )
            when (data) {
                is NetworkResult.Success -> {
                    _mutableState.value = AgreementState.Action(action)
                }

                is NetworkResult.Error -> {
                    _mutableState.value = AgreementState.ActionError(data.error)
                }
            }

        }
    }

    fun getData() {
        if (config == null) return
        viewModelScope.launch {

            val data = api.getData(
                url,
                config!!.appId,
                config!!.version,
                config!!.deviceId ?: "",
                BuildConfig.LIB_VERSION_NAME,
                config!!.name
            )
            when (data) {
                is NetworkResult.Success -> {

                    val response = data.value?.toDomain(config?.lang)
                    if (response == null || response.id == null) {
                        _mutableState.value = AgreementState.NoContent
                    } else {
                        itemId = response.id
                        _mutableState.value = AgreementState.ShowView(response)
                        sendAction(Actions.VIEW.value)

                    }
                }

                is NetworkResult.Error -> {

                    _mutableState.value = AgreementState.Error(data.error)
                }
            }
        }

    }

    private val _openDialog = MutableStateFlow(false)
    val openDialog: StateFlow<Boolean> = _openDialog.asStateFlow()

    fun showDialog() {
        _openDialog.value = true
    }

    fun submitAccept() {
        sendAction(Actions.ACCEPT.value)
        _openDialog.value = false
        clearState()
    }

    fun dismissDialog() {
        sendAction(Actions.DECLINE.value)
        _openDialog.value = false
        triggerForceUpdate()
        clearState()
    }


    private val _cancelButtonEvent = Channel<Unit>(Channel.BUFFERED)
    val cancelButtonEvent = _cancelButtonEvent.receiveAsFlow()

    fun triggerForceUpdate() {
        viewModelScope.launch {
            _cancelButtonEvent.send(Unit)
        }
    }
}