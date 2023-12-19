package com.etac.service.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etac.service.callbacks.ApplicationStatusCallback
import com.etac.service.models.ApplicationStatus
import com.etac.service.models.ErrorResponse
import com.etac.service.repositories.AuthRepository
import com.etac.service.utils.ApiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(),
        ApplicationStatusCallback
{
    var applicationStatusRes = MutableLiveData<ApiEvent<ApplicationStatus>>()
    var errorResponse = MutableLiveData<ApiEvent<ErrorResponse>>()
    fun getApplicationStatus(url:String){
        viewModelScope.launch {
            repository.getApplicationStatus(url,this@AuthViewModel)
        }
    }

    override fun onApplicationRes(data: ApplicationStatus) {
        applicationStatusRes.value = ApiEvent(data)
    }
    override fun onErrorRes(error: ErrorResponse) {
        errorResponse.value = ApiEvent(error)
    }
}