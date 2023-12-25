package com.etac.service.callbacks.general

import com.etac.service.models.ErrorResponse
import com.etac.service.models.SubmitPaymentInfoResponse

interface SubmitPaymentInfoCallback {
    fun onPaymentInfoResponse(data: SubmitPaymentInfoResponse)
    fun onErrorRes(error: ErrorResponse)
}