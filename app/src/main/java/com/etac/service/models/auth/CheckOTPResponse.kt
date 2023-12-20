package com.etac.service.models.auth

data class CheckOTPResponse(
    val result: OTPResult? = null,
    val maintenance_info: Any? = null,
    val result_code: Int? = null,
    val time: String? = null
)

data class OTPResult(
    val is_valid: Boolean? = false,
)