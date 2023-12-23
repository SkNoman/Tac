package com.etac.service.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentOTPBinding
import com.etac.service.models.auth.UserInfo
import com.etac.service.network.ApiEndPoint
import com.etac.service.shared_preference.SharedPref
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
    private val args by navArgs<OTPFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userInfo = args.userInfo

        binding.btnValidateOTP.setOnClickListener {
            if (binding.layoutOTP.text?.length == 6){
                checkOTP(binding.layoutOTP.text.toString(),userInfo)
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
                            checkOTP(enteredOTP,userInfo)
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
                        try {
                            SharedPref(requireContext()).saveUserInfo(userInfo)
                            onLoadingVm().showLoadingFun(false)
                            AppUtils.showToast(requireContext(),
                                               "Welcome ${userInfo.name}", true, getString(R.string.toast_type_success))
                            findNavController().navigate(R.id.dashboardFragment,null,
                                                         Animation.animNav().build())
                        }catch (e:Exception){
                            AppUtils.showToast(requireContext(),getString(R.string.invalid_otp),true,
                                              Constant.ERROR_MESSAGE)
                        }
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

    private fun checkOTP(otp:String,userInfo: UserInfo) {
        onLoadingVm().showLoadingFun(true)
        try {
            if (userInfo.phoneNumber.isNotEmpty()){
                val jsonObject = JsonObject()
                jsonObject.addProperty("phone",userInfo.phoneNumber)
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