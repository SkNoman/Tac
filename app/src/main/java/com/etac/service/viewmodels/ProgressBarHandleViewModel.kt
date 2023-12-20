package com.etac.service.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgressBarHandleViewModel @Inject constructor() : ViewModel() {

    private val showLoadingMutableData: MutableLiveData<Boolean> = MutableLiveData()
    val showLoadingLiveData: LiveData<Boolean> get() = showLoadingMutableData

    fun showLoadingFun(value: Boolean) {
        showLoadingMutableData.postValue(value)
    }

}