package com.etac.service.models

data class ApplicationStatus(
	val result: Result? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class Result(
	val current_version: Int,
	val force_update: Boolean? = false
)

