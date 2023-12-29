package com.etac.service.utils

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                // Empty body
                if (body == null || response.code() == 204) {
                    ApiSuccessEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMessage ?: "Unknown error")
            }
        }
    }
}

class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>() {
    override fun toString(): String {
        return "$data"
    }
}

class ApiSuccessEmptyResponse<T> : ApiResponse<T>()
class ApiErrorResponse<T>(private val errorMessage: String) : ApiResponse<T>() {
    override fun toString(): String {
        return "ApiErrorResponse(errorMessage='$errorMessage')"
    }
}

