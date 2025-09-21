package com.sepanta.controlkit.agreementkit.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sepanta.controlkit.agreementkit.service.AgreementApi

class AgreementViewModelFactory(
    private val api: AgreementApi,

    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgreementViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AgreementViewModel(api) as T
        }
        throw kotlin.IllegalArgumentException("Unknown ViewModel class")
    }
}