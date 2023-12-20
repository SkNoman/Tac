package com.etac.service.callbacks.auth

import com.etac.service.models.ErrorResponse
import com.etac.service.models.auth.CheckOTPResponse
import com.etac.service.models.auth.SignUpResponse

interface CheckOTPCallback {
    fun onOTPRes(data: CheckOTPResponse)
    fun onErrorRes(error: ErrorResponse)
}