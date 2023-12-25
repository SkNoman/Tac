package com.etac.service.models.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class UserInfo (
    val name: String? = null,
    val phoneNumber: String? = null,
    val alternativePhoneNumber: String? = null,
    val area: String? = null,
    val address: String? = null
): Parcelable