package com.etac.service.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etac.service.callbacks.auth.ApplicationStatusCallback
import com.etac.service.callbacks.auth.CheckOTPCallback
import com.etac.service.callbacks.auth.LoginCallback
import com.etac.service.callbacks.auth.SignUpCallback
import com.etac.service.models.ApplicationStatusResponse
import com.etac.service.models.ErrorResponse
import com.etac.service.models.auth.CheckOTPResponse
import com.etac.service.models.auth.LoginResponse
import com.etac.service.models.auth.SignUpResponse
import com.etac.service.repositories.AuthRepository
import com.etac.service.utils.ApiEvent
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(),
    ApplicationStatusCallback,LoginCallback,SignUpCallback,CheckOTPCallback
{
    var applicationStatusRes = MutableLiveData<ApiEvent<ApplicationStatusResponse>>()
    var checkUserRes = MutableLiveData<ApiEvent<LoginResponse>>()
    var userSignUpRes = MutableLiveData<ApiEvent<SignUpResponse>>()
    var checkOTPRes = MutableLiveData<ApiEvent<CheckOTPResponse>>()
    var errorResponse = MutableLiveData<ApiEvent<ErrorResponse>>()
    fun getApplicationStatus(url:String){
        viewModelScope.launch {
            repository.getApplicationStatus(url,this@AuthViewModel)
        }
    }

    fun checkUser(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.checkUser(url,jsonObject,this@AuthViewModel)
        }
    }

    fun userSignUp(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.userSignUp(url,jsonObject,this@AuthViewModel)
        }
    }

    fun checkOTP(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.checkOTP(url,jsonObject,this@AuthViewModel)
        }
    }

    override fun onApplicationRes(data: ApplicationStatusResponse) {
        applicationStatusRes.value = ApiEvent(data)
    }

    override fun onLoginRes(data: LoginResponse) {
        checkUserRes.value = ApiEvent(data)
    }

    override fun onSingUpRes(data: SignUpResponse) {
        userSignUpRes.value = ApiEvent(data)
    }

    override fun onOTPRes(data: CheckOTPResponse) {
        checkOTPRes.value = ApiEvent(data)
    }

    override fun onErrorRes(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }
}