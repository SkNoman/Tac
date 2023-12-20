package com.etac.service.repositories

import com.etac.service.callbacks.auth.ApplicationStatusCallback
import com.etac.service.callbacks.auth.CheckOTPCallback
import com.etac.service.callbacks.auth.LoginCallback
import com.etac.service.callbacks.auth.SignUpCallback
import com.etac.service.models.ApplicationStatusResponse
import com.etac.service.models.ErrorResponse
import com.etac.service.models.auth.CheckOTPResponse
import com.etac.service.models.auth.LoginResponse
import com.etac.service.models.auth.SignUpResponse
import com.etac.service.network.ApiInterface
import com.etac.service.utils.Constant
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api:ApiInterface)
{
    fun getApplicationStatus(url:String,onCallback: ApplicationStatusCallback){
        api.getApplicationStatus(url).enqueue(object: retrofit2.Callback<ApplicationStatusResponse>{
            override fun onResponse(
                call: Call<ApplicationStatusResponse>,
                response: Response<ApplicationStatusResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onApplicationRes(response.body()!!)
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<ApplicationStatusResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
            }
        })
    }

    fun checkUser(url:String,jsonObject: JsonObject,onCallback: LoginCallback){
        api.checkUser(url,jsonObject).enqueue(object: retrofit2.Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onLoginRes(response.body()!!)
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
            }
        })
    }

    fun userSignUp(url:String,jsonObject: JsonObject,onCallback: SignUpCallback){
        api.postSignUp(url,jsonObject).enqueue(object: retrofit2.Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onSingUpRes(response.body()!!)
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
            }
        })
    }

    fun checkOTP(url:String,jsonObject: JsonObject,onCallback: CheckOTPCallback){
        api.checkOTP(url,jsonObject).enqueue(object: retrofit2.Callback<CheckOTPResponse>{
            override fun onResponse(
                call: Call<CheckOTPResponse>,
                response: Response<CheckOTPResponse>
            ) {
                if (response.code() == 200){
                    onCallback.onOTPRes(response.body()!!)
                }else{
                    onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
                }
            }
            override fun onFailure(call: Call<CheckOTPResponse>, t: Throwable) {
                onCallback.onErrorRes(ErrorResponse(-1,Constant.ERROR_MESSAGE))
            }
        })
    }
}