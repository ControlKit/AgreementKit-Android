package com.sepanta.controlkit.agreementkit.view.viewmodel.state

import com.sepanta.controlkit.agreementkit.service.model.DataResponse
import com.sepanta.errorhandler.ApiError


sealed class AgreementState {
    object Initial : AgreementState()
    object NoContent : AgreementState()
    data class ShowView(val data: DataResponse?) : AgreementState()
    data class Action(val data: String?) : AgreementState()
    data class Error(val data: ApiError<*>?) : AgreementState()
    data class ActionError(val data: ApiError<*>?) : AgreementState()


}

