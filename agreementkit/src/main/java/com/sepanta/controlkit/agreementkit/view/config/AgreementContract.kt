package com.sepanta.controlkit.agreementkit.view.config

import androidx.compose.runtime.Composable
import com.sepanta.controlkit.agreementkit.service.model.DataResponse
import com.sepanta.controlkit.agreementkit.view.viewmodel.AgreementViewModel

interface AgreementContract {
    @Composable
    fun ShowView(
        config: AgreementViewConfig,
        response: DataResponse,
        viewModel: AgreementViewModel
    )
}