package com.etac.service.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etac.service.callbacks.services.ServiceCreateCallback
import com.etac.service.models.ErrorResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.repositories.ServiceRepository
import com.etac.service.utils.ApiEvent
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(private val repository: ServiceRepository):ViewModel(),
        ServiceCreateCallback
{
    var createServiceResponse = MutableLiveData<ApiEvent<ServiceCreateResponse>>()
    var errorResponse = MutableLiveData<ApiEvent<ErrorResponse>>()

    fun createServiceReq(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.createServiceRequest(url,jsonObject,this@ServiceViewModel)
        }
    }
    override fun onCreateService(data: ServiceCreateResponse) {
        createServiceResponse.value = ApiEvent(data)
    }

    override fun onErrorRes(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }

}