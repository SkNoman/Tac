package com.etac.service.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etac.service.callbacks.services.ServiceCreateCallback
import com.etac.service.callbacks.services.ServiceListCallback
import com.etac.service.models.ErrorResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.etac.service.models.service.ServiceListResponse
import com.etac.service.repositories.ServiceRepository
import com.etac.service.utils.ApiEvent
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(private val repository: ServiceRepository):ViewModel(),
        ServiceCreateCallback, ServiceListCallback
{
    var createServiceRes = MutableLiveData<ApiEvent<ServiceCreateResponse>>()
    var serviceListRes = MutableLiveData<ApiEvent<ServiceListResponse>>()
    var errorResponse = MutableLiveData<ApiEvent<ErrorResponse>>()

    fun createServiceReq(url:String,jsonObject: JsonObject){
        viewModelScope.launch {
            repository.createServiceRequest(url,jsonObject,this@ServiceViewModel)
        }
    }
    fun getServiceList(url: String,phone: String){
        viewModelScope.launch {
            repository.getServiceList(url,phone,this@ServiceViewModel)
        }
    }
    override fun onCreateService(data: ServiceCreateResponse) {
        createServiceRes.value = ApiEvent(data)
    }

    override fun onServiceList(data: ServiceListResponse) {
        serviceListRes.value = ApiEvent(data)
    }

    override fun onErrorRes(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }

}