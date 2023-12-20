package com.etac.service.models.service

data class ServiceCreateResponse(
	val result: ServiceCreateResult? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class ServiceCreateResult(
	val message: String? = null,
	val id: Int? = null
)

