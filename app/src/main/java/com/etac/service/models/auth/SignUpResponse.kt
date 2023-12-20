package com.etac.service.models.auth

data class SignUpResponse(
    val result: SignUpResult? = null,
    val maintenance_info: Any? = null,
    val result_code: Int? = null,
    val time: String? = null
)

data class SignUpResult(
    val message: String? = null,
)