package com.etac.service.repositories

import com.etac.service.callbacks.general.UpdateUserInfoCallback
import com.etac.service.callbacks.services.ServiceCreateCallback
import com.etac.service.callbacks.services.ServiceListCallback
import com.etac.service.models.ErrorResponse
import com.etac.service.models.UpdateUserInfoResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.models.service.ServiceListResponse
import com.etac.service.network.ApiInterface
import com.etac.service.utils.Constant
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class GeneralRepository @Inject constructor(private val api: ApiInterface) {
    fun updateUserInfo(url:String, jsonObject: JsonObject, onCallback: UpdateUserInfoCallback){
        api.updateUserInfo(url,jsonObject).enqueue(object: retrofit2.Callback<UpdateUserInfoResponse>{
            override fun onResponse(
                call: Call<UpdateUserInfoResponse>,
                response: Response<UpdateUserInfoResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onUpdateResponse(response.body()!!)
                }else if (response.code() == 400){
                    onCallback.onUpdateErrorResponse(ErrorResponse(-1, "Invalid Request"))
                }else{
                    onCallback.onUpdateErrorResponse(ErrorResponse(-1, Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<UpdateUserInfoResponse>, t: Throwable) {
                onCallback.onUpdateErrorResponse(ErrorResponse(-1, Constant.ERROR_MESSAGE))
            }
        })
    }
}