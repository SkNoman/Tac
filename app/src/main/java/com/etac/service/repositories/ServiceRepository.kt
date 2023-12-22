package com.etac.service.repositories

import com.etac.service.callbacks.services.ServiceCreateCallback
import com.etac.service.callbacks.services.ServiceListCallback
import com.etac.service.models.ErrorResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.models.service.ServiceListResponse
import com.etac.service.network.ApiInterface
import com.etac.service.utils.Constant
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ServiceRepository @Inject constructor(private val api: ApiInterface) {
    fun createServiceRequest(url:String, jsonObject: JsonObject, onCallback: ServiceCreateCallback){
        api.createServiceRea(url,jsonObject).enqueue(object: retrofit2.Callback<ServiceCreateResponse>{
            override fun onResponse(
                call: Call<ServiceCreateResponse>,
                response: Response<ServiceCreateResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onCreateService(response.body()!!)
                }else if (response.code() == 400){
                    onCallback.onErrorRes(ErrorResponse(-1, "Invalid Request"))
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1, Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<ServiceCreateResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1, Constant.ERROR_MESSAGE))
            }
        })
    }
    fun getServiceList(url:String, phone: String, onCallback: ServiceListCallback){
        api.getServiceList(url,phone).enqueue(object: retrofit2.Callback<ServiceListResponse>{
            override fun onResponse(
                call: Call<ServiceListResponse>,
                response: Response<ServiceListResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onServiceList(response.body()!!)
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1, Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<ServiceListResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1, Constant.ERROR_MESSAGE))
            }
        })
    }
}