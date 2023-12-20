package com.etac.service.callbacks.services

import com.etac.service.models.ErrorResponse
import com.etac.service.models.service.ServiceCreateResponse

interface ServiceCreateCallback {
    fun  onCreateService(data: ServiceCreateResponse)
    fun onErrorRes(error: ErrorResponse)
}