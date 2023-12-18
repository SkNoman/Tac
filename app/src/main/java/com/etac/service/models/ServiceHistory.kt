package com.etac.service.models

class ServiceHistory {
    val list: List<ServiceHistoryList>? = null
}

data class  ServiceHistoryList(
    val id: Int?,
    val serviceType : Int?,
    val serviceTitle: String? = "",
    val serviceDetails: String? = "",
    val serviceDate: String? = "",
    val serviceStatus: Int
)