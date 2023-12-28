package com.etac.service.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSignInBinding
import com.etac.service.models.auth.LoginUserInfo
import com.etac.service.models.auth.UserInfo
import com.etac.service.network.ApiEndPoint
import com.etac.service.shared_preference.SharedPref
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.AuthViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragmentWithBinding<FragmentSignInBinding>
    (FragmentSignInBinding::inflate)
{
        private val authViewModel: AuthViewModel by viewModels()

        override fun onResume() {
            super.onResume()
            SharedPref(requireContext()).clearUserInfo()
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            /*binding.tvNavigateSignIn.setOnClickListener {
                findNavController().navigate(R.id.signUpFragment ,null , Animation.animNav().build())
            }*/
            binding.btnSignIn.setOnClickListener {
                CheckNetworkStatus.isOnline(requireContext(),object:CheckNetworkStatus.Status{
                    override fun online() {
                        if (binding.etPhoneNumber.text.length == 11){
                            checkUserPhoneNumber()
                        }else{
                            AppUtils.showToast(requireContext(),
                            getString(R.string.please_enter_valid_phone_number), false,getString(R.string.toast_type_warning))
                        }
                    }
                    override fun offline() {
                        AppUtils.showToast(requireContext(),
                        getString(R.string.pls_check_internet),false, getString(R.string.toast_type_warning))
                    }
                })
            }

            authViewModel.checkUserRes.observe(viewLifecycleOwner) { data ->
                data.getContentIfNotHandled().let {
                    if (it?.result_code == 0) {
                        onLoadingVm().showLoadingFun(false)
                        when (it.result?.hasUser) {
                            0 -> {
                                val action = SignInFragmentDirections.actionSignInFragmentToOTPFragment(
                                        UserInfo(
                                                null,
                                                binding.etPhoneNumber.text.toString(),
                                               null,
                                                null,
                                               null))
                                findNavController().navigate(action,Animation.animNav().build())
                            }
                            1 -> { //VALID USER HAVE USER INFO
                                val userInfo = it.result.data
                                val action = SignInFragmentDirections.actionSignInFragmentToOTPFragment(
                                        UserInfo(
                                                userInfo.name,
                                                userInfo.primary_phone,
                                                userInfo.alternative_phone,
                                                userInfo.area,
                                                userInfo.address))
                                findNavController().navigate(action,Animation.animNav().build())
                            }
                            else -> { //BLOCKED USER
                                AppUtils.showToast(requireContext(),
                                it.result?.message.toString(), false,getString(R.string.toast_type_error))
                            }
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

    private fun checkUserPhoneNumber() {
        try {
            onLoadingVm().showLoadingFun(true)
            val jsonObject = JsonObject()
            jsonObject.addProperty("user_type",Constant.USER_TYPE)
            jsonObject.addProperty("primary_phone",binding.etPhoneNumber.text.toString())
            authViewModel.checkUser(ApiEndPoint.LOGIN,jsonObject)

        }catch (e:Exception){
            onLoadingVm().showLoadingFun(false)
            AppUtils.showToast(requireContext(),
                               Constant.ERROR_MESSAGE, false, getString(R.string.toast_type_error))
        }
    }
}