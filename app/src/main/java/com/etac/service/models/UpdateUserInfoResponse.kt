package com.etac.service.models

data class UpdateUserInfoResponse(
	val result: UpdateUserInfoResult? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class UpdateUserInfoResult(
	val message: String? = null,
)

