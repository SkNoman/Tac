package com.etac.service.models

class ServiceHistory {
    val list: List<ServiceHistoryList>? = null
}

data class  ServiceHistoryList(
    val serviceType : Int?,
    val serviceTitle: String? = "",
    val serviceDetails: String? = "",
    val serviceDate: String? = ""
)