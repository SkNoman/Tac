package com.etac.service.callbacks.auth

import com.etac.service.models.ApplicationStatusResponse
import com.etac.service.models.ErrorResponse

interface ApplicationStatusCallback {
    fun onApplicationRes(data: ApplicationStatusResponse)
    fun onErrorRes(error: ErrorResponse)
}