package com.etac.service.callbacks.auth

import com.etac.service.models.ErrorResponse
import com.etac.service.models.auth.LoginResponse

interface LoginCallback {
    fun onLoginRes(data: LoginResponse)
    fun onErrorRes(error: ErrorResponse)
}