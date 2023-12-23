package com.etac.service.callbacks.general

import com.etac.service.models.ErrorResponse
import com.etac.service.models.UpdateUserInfoResponse

interface UpdateUserInfoCallback {
    fun onUpdateResponse(data: UpdateUserInfoResponse)
    fun onUpdateErrorResponse(error: ErrorResponse)
}