package com.etac.service.callbacks.services

import com.etac.service.models.ErrorResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.models.service.ServiceListResponse

interface ServiceListCallback {
    fun  onServiceList(data: ServiceListResponse)
    fun onErrorRes(error: ErrorResponse)
}