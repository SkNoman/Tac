package com.etac.service.models

data class SubmitPaymentInfoResponse(
	val result: SubmitPaymentInfoResult? = null,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class SubmitPaymentInfoResult(
	val message: String? = null,
)

