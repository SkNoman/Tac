package com.etac.service.models.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class UserInfo (
    val name: String,
    val phoneNumber: String,
    val alternativePhoneNumber: String,
    val area: String,
    val address: String
): Parcelable