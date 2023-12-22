package com.etac.service.ui.services

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentLaundryServiceBinding
import com.etac.service.models.service.areaList
import com.etac.service.network.ApiEndPoint
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.ServiceViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaundryServiceFragment : BaseFragmentWithBinding<FragmentLaundryServiceBinding>(
        FragmentLaundryServiceBinding::inflate)
{
    private val serviceViewModel: ServiceViewModel by viewModels()
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }
        binding.btnSubmit.setOnClickListener {
            CheckNetworkStatus.isOnline(requireContext(), object: CheckNetworkStatus.Status{
                override fun online() {
                    if (userInputValidation() == "ok"){
                        submitServiceRequest()
                    }else{
                        AppUtils.showToast(requireContext(),
                                userInputValidation(), false, getString(R.string.toast_type_warning))
                    }
                }

                override fun offline() {
                    AppUtils.showToast(requireContext(),
                                       getString(R.string.pls_check_internet), false, getString(R.string.toast_type_warning))
                }

            })
        }


        val arrayAdapter = ArrayAdapter(requireActivity(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,areaList)
        binding.etArea.setAdapter(arrayAdapter)

        serviceViewModel.createServiceRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0 && it.result?.message == "SUCCESS") {
                    onLoadingVm().showLoadingFun(false)
                    AppUtils.showToast(requireContext(),
                                       it.result.message.toString(), true, getString(R.string.toast_type_success))
                    findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
                }
            }
        }
        serviceViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            onLoadingVm().showLoadingFun(false)
            error.getContentIfNotHandled().let {
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false, getString(R.string.toast_type_error))
            }
        }
    }

    private fun submitServiceRequest() {
        onLoadingVm().showLoadingFun(true)
        try {
            val jsonObject = JsonObject()
            jsonObject.addProperty("phone",binding.etPhoneNumber.text.toString())
            jsonObject.addProperty("area",binding.etArea.text.toString())
            jsonObject.addProperty("address",binding.etAddress.text.toString())
            jsonObject.addProperty("service_type","laundry")
            jsonObject.addProperty("service_name","laundry")
            jsonObject.addProperty("service_details",binding.etServiceDetails.text.toString())
            serviceViewModel.createServiceReq(ApiEndPoint.CREATE_SERVICE_REQ, jsonObject)
        }catch (e:Exception){
            onLoadingVm().showLoadingFun(false)
            AppUtils.showToast(requireContext(),
                               Constant.ERROR_MESSAGE, false, getString(R.string.toast_type_warning))
        }
    }
    private fun userInputValidation():String {
        return if(binding.etName.text?.length!! <3){
            getString(R.string.please_enter_your_full_name)
        }else if(binding.etPhoneNumber.text?.length != 11){
            getString(R.string.please_enter_valid_phone_number)
        }else if(binding.etAddress.text?.length!! < 4){
            getString(R.string.please_enter_your_valid_address)
        }else if (binding.etArea.text.isNullOrEmpty()){
            return getString(R.string.please_select_your_area)
        }
        else{
            "ok"
        }
    }
}