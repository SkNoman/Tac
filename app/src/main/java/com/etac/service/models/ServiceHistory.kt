package com.etac.service.models

class ServiceHistory {
    val list: List<ServiceHistoryList>? = null
}

data class  ServiceHistoryList(
    val menuId: Int? = null,
    val menuTitle: String? = "",
    val menuDetails: String? = null,
    val menuIcon: Int? = null,
    val serviceDate: String? = null
)