package com.etac.service.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentOTPBinding
import com.etac.service.network.ApiEndPoint
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.utils.HideKeyboard.hideKeyboard
import com.etac.service.viewmodels.AuthViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPFragment: BaseFragmentWithBinding<FragmentOTPBinding>
    (FragmentOTPBinding::inflate)
{
    private val authViewModel: AuthViewModel by viewModels()
    private  var phone = ""
    private var name = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            phone = requireArguments().getString("phone","")
            name = requireArguments().getString("name","")
        }catch (e:Exception){
            AppUtils.showToast(requireContext(),Constant.ERROR_MESSAGE,false,
                               getString(R.string.toast_type_error))
        }



        binding.btnValidateOTP.setOnClickListener {
            if (binding.layoutOTP.text?.length == 6){
                checkOTP(binding.layoutOTP.text.toString())
            }else {
                AppUtils.showToast(requireContext(),getString(R.string.invalid_otp),false,
                                   getString(R.string.toast_type_error))
            }
        }

        binding.layoutOTP.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Check if the OTP is complete
                if (editable?.length == binding.layoutOTP.itemCount) {
                    // Perform your OTP validation here
                    requireActivity().hideKeyboard()
                    val enteredOTP = editable.toString()
                    // For example, you can check if the entered OTP is "123456"
                    CheckNetworkStatus.isOnline(requireContext(),object :CheckNetworkStatus.Status{
                        override fun online() {
                            checkOTP(enteredOTP)
                        }

                        override fun offline() {
                            AppUtils.showToast(requireContext(),getString(R.string.pls_check_internet),
                                               false,getString(R.string.toast_type_error))
                        }

                    })
                }
            }
        })

        authViewModel.checkOTPRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0) {
                    if (it.result?.is_valid == true) {
                        onLoadingVm().showLoadingFun(false)
                        AppUtils.showToast(requireContext(),
                                           "Welcome $name", true, getString(R.string.toast_type_success))
                        findNavController().navigate(R.id.dashboardFragment,null,
                                                     Animation.animNav().build())
                    }else{
                        onLoadingVm().showLoadingFun(false)
                        AppUtils.showToast(requireContext(),getString(R.string.invalid_otp),true,
                                           getString(R.string.toast_type_error))
                    }
                }
            }
        }
        authViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            onLoadingVm().showLoadingFun(false)
            error.getContentIfNotHandled().let {
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false, getString(R.string.toast_type_error))
            }
        }
    }

    private fun checkOTP(otp:String) {
        onLoadingVm().showLoadingFun(true)
        try {
            if (phone.isNotEmpty()){
                val jsonObject = JsonObject()
                jsonObject.addProperty("phone",phone)
                jsonObject.addProperty("otp",otp)
                authViewModel.checkOTP(ApiEndPoint.CHECK_OTP,jsonObject)
            }else{
                AppUtils.showToast(requireContext(),Constant.ERROR_MESSAGE,false,
                                   getString(R.string.toast_type_error))
                findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
            }

        }catch (e:Exception){
            AppUtils.showToast(requireContext(),Constant.ERROR_MESSAGE,false,
                               getString(R.string.toast_type_error))
        }
    }
}