package com.etac.service.repositories

import com.etac.service.callbacks.ApplicationStatusCallback
import com.etac.service.models.ApplicationStatus
import com.etac.service.models.ErrorResponse
import com.etac.service.network.ApiInterface
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api:ApiInterface)
{
    fun getApplicationStatus(url:String,onCallback:ApplicationStatusCallback){
        api.getApplicationStatus(url).enqueue(object: retrofit2.Callback<ApplicationStatus>{
            override fun onResponse(
                call: Call<ApplicationStatus>,
                response: Response<ApplicationStatus>
            ) {
                onCallback.onApplicationRes(response.body()!!)
            }

            override fun onFailure(call: Call<ApplicationStatus>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1,t.message.toString()))
            }

        })
    }
}