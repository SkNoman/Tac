package com.etac.service.models.service


data class ServiceListResponse(
	val result: ServiceHistoryResult,
	val maintenance_info: Any? = null,
	val result_code: Int? = null,
	val time: String? = null
)

data class ServiceHistoryResult(
	val data: List<ServiceHistoryList>
)
data class ServiceHistoryList(
	val area: String? = null,
	val service_details: String? = null,
	val service_type: Int? = null,
	val address: String? = null,
	val phone: String? = null,
	val service_Name: String? = null,
	val service_status: Int? = 0,
	val created_at: String? = null,
	val id: Int? = null
)

