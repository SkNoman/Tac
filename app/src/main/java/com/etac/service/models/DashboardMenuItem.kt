package com.etac.service.models

class DashboardMenuItem {
    val menus: List<MenusItem>? = null
}

data class  MenusItem(
    val menuId: Int? = null,
    val menuTitle: String? = "",
    val menuServiceCount: Int? = null,
    val menuIcon: Int? = null,
    val menuBGColor: Int? = null
)