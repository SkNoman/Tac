package com.etac.service.models.auth

data class LoginResponse(
	val result: Result? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class Result(
	val message: String? = null,
	val hasUser: Boolean? = null
)

