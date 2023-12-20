package com.etac.service.callbacks.auth

import com.etac.service.models.ErrorResponse
import com.etac.service.models.auth.SignUpResponse

interface SignUpCallback {
    fun onSingUpRes(data: SignUpResponse)
    fun onErrorRes(error: ErrorResponse)
}