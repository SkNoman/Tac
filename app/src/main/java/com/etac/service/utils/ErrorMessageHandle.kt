package com.etac.service.utils

import com.etac.service.models.ErrorResponse
import com.google.gson.Gson
import java.io.Reader

object ErrorMessageHandle {

    fun getErrorMessageFromResponseBody(errorBody: Reader) : ErrorResponse {

        val gson = Gson()
        val errorBodyObject = gson.fromJson(
            errorBody,
            ErrorJson::class.java

        )

        return ErrorResponse(errorBodyObject.resultCode,
            errorBodyObject.error.message)

    }
}