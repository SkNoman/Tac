package com.etac.service.callbacks

import com.etac.service.models.ApplicationStatus
import com.etac.service.models.ErrorResponse

interface ApplicationStatusCallback {
    fun onApplicationRes(data: ApplicationStatus)
    fun onErrorRes(error: ErrorResponse)
}