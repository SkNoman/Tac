package com.etac.service.utils


import com.google.gson.annotations.SerializedName

data class ErrorJson(
    @SerializedName("error")
    val error: Error,
    @SerializedName("result_code")
    val resultCode: Int, // 23
    @SerializedName("time")
    val time: String // 2021-11-29 11:36:22
) {
    data class Error(
        @SerializedName("message")
        val message: String, // Mobile/Password did not match
        @SerializedName("title")
        val title: String // Invalid Password
    )
}