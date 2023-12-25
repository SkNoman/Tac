package com.etac.service.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etac.service.callbacks.general.SubmitPaymentInfoCallback
import com.etac.service.callbacks.general.UpdateUserInfoCallback
import com.etac.service.callbacks.services.ServiceCreateCallback
import com.etac.service.callbacks.services.ServiceListCallback
import com.etac.service.models.ErrorResponse
import com.etac.service.models.SubmitPaymentInfoResponse
import com.etac.service.models.UpdateUserInfoResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.models.service.ServiceListResponse
import com.etac.service.repositories.GeneralRepository
import com.etac.service.repositories.ServiceRepository
import com.etac.service.utils.ApiEvent
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(private val repository: GeneralRepository):ViewModel(),
        UpdateUserInfoCallback,SubmitPaymentInfoCallback
{
    var updateUserInfoRes = MutableLiveData<ApiEvent<UpdateUserInfoResponse>>()
    var submitPaymentInfoRes = MutableLiveData<ApiEvent<SubmitPaymentInfoResponse>>()
    var errorResponse = MutableLiveData<ApiEvent<ErrorResponse>>()

    fun updateUserInfo(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.updateUserInfo(url,jsonObject,this@GeneralViewModel)
        }
    }
    fun submitPaymentInfo(url: String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.submitPaymentInfo(url,jsonObject,this@GeneralViewModel)
        }
    }

    override fun onUpdateResponse(data: UpdateUserInfoResponse) {
        updateUserInfoRes.value = ApiEvent(data)
    }

    override fun onUpdateErrorResponse(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }

    override fun onPaymentInfoResponse(data: SubmitPaymentInfoResponse) {
        submitPaymentInfoRes.value = ApiEvent(data)
    }
    override fun onErrorRes(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }


}