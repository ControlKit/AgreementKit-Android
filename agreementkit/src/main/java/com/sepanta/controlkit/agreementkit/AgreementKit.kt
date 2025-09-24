package com.sepanta.controlkit.agreementkit

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sepanta.controlkit.agreementkit.service.AgreementApi
import com.sepanta.controlkit.agreementkit.service.ApiService
import com.sepanta.controlkit.agreementkit.service.RetrofitClientInstance
import com.sepanta.controlkit.agreementkit.service.config.AgreementServiceConfig
import com.sepanta.controlkit.agreementkit.service.model.DataResponse
import com.sepanta.controlkit.agreementkit.utils.UniqueUserIdProvider
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewStyle
import com.sepanta.controlkit.agreementkit.view.viewmodel.AgreementViewModel
import com.sepanta.controlkit.agreementkit.view.viewmodel.AgreementViewModelFactory
import com.sepanta.controlkit.agreementkit.view.viewmodel.state.AgreementState

class AgreementKit(
    private var config: AgreementServiceConfig,
    context: Context,

    ) {

    private var _viewModel: AgreementViewModel? = null

    init {
        setupViewModel(context)
    }

    private fun setupViewModel(context: Context) {
        val retrofit = RetrofitClientInstance.getRetrofitInstance(
            config.timeOut,
            config.maxRetry,
            config.timeRetryThreadSleep
        ) ?: return

        val api = AgreementApi(retrofit.create(ApiService::class.java))
        _viewModel = AgreementViewModelFactory(api).create(AgreementViewModel::class.java)
        if (config.deviceId == null) {
            config.deviceId = UniqueUserIdProvider.getOrCreateUserId(context)
            _viewModel?.setConfig(config)

        } else {
            _viewModel?.setConfig(config)

        }
    }


    @Composable
    internal fun ConfigureComposable(
        onDismiss: (() -> Unit)? = null,
        onState: ((AgreementState) -> Unit)? = null,
    ) {
        if (_viewModel == null) return

        val state = _viewModel?.state?.collectAsState()?.value
        val response = remember { mutableStateOf<DataResponse?>(null) }

        LaunchedEffect(Unit) {
            _viewModel?.cancelButtonEvent?.collect {
                onDismiss?.invoke()
            }
        }
        InitView(response)

        when (state) {
            AgreementState.Initial -> {

                onState?.invoke(AgreementState.Initial)
            }

            is AgreementState.Action -> onState?.invoke(AgreementState.Action(state.data))
            AgreementState.NoContent -> onState?.invoke(AgreementState.NoContent)

            is AgreementState.ShowView -> state.data?.let {
                response.value = it
                onState?.invoke(AgreementState.ShowView(it))
                _viewModel?.showDialog()

            }

            is AgreementState.Error -> onState?.invoke(AgreementState.Error(state.data))
            is AgreementState.ActionError -> onState?.invoke(AgreementState.Error(state.data))

            else -> Unit
        }
    }

    @Composable
    private fun InitView(response: MutableState<DataResponse?>) {
        response.value?.let { data ->
            AgreementViewStyle.checkViewStyle(config.viewConfig.viewStyle)
                .ShowView(config = config.viewConfig, data, _viewModel!!)
        }
    }

    fun showView() {
        _viewModel?.getData()
    }
}

@Composable
fun agreementKitHost(
    config: AgreementServiceConfig,
    onDismiss: (() -> Unit)? = null,
    onState: ((AgreementState) -> Unit)? = null,
): AgreementKit {
    val context = LocalContext.current
    val kit = remember { AgreementKit(config, context) }
    kit.ConfigureComposable(onState = onState, onDismiss = onDismiss)
    return kit
}




