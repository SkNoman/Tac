package com.etac.service.models.auth

data class LoginResponse(
	val result: Result? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class Result(
	val message: String? = null,
	val hasUser: Int? = null,
	val data: LoginUserInfo
)
data class LoginUserInfo (
	val name: String = "",
	val primary_phone: String = "",
	val alternative_phone: String = "",
	val area: String = "",
	val address: String = ""
)

