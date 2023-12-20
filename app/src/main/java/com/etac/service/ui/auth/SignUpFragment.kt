package com.etac.service.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSignUpBinding
import com.etac.service.network.ApiEndPoint
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.AuthViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragmentWithBinding<FragmentSignUpBinding>
    (FragmentSignUpBinding::inflate)
{
    private val authViewModel: AuthViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.layoutNavigateSignIn.setOnClickListener{
            findNavController().navigate(R.id.signInFragment , null , Animation.animNav().build())
        }

        binding.btnSignUp.setOnClickListener{
            CheckNetworkStatus.isOnline(requireContext(),object: CheckNetworkStatus.Status{
                override fun online() {
                    if (validateUserInputs() == "ok"){
                        signUp()
                    }else{
                        AppUtils.showToast(requireContext(),
                                           validateUserInputs(), false, getString(R.string.toast_type_error))
                    }
                }
                override fun offline() {
                    AppUtils.showToast(requireContext(),
                                       getString(R.string.pls_check_internet), false, getString(R.string.toast_type_error))
                }

            })
        }
        authViewModel.userSignUpRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0 && it.result?.message == "SUCCESS") {
                    onLoadingVm().showLoadingFun(false)
                    AppUtils.showToast(requireContext(),
                                       it.result.message.toString(), false, getString(R.string.toast_type_success))
                    val bundle = Bundle()
                    bundle.putString("name",binding.etFullName.text.toString())
                    bundle.putString("phone",binding.etPhoneNumber.text.toString())
                    findNavController().navigate(R.id.OTPFragment,bundle,Animation.animNav().build())
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

    private fun signUp() {

        onLoadingVm().showLoadingFun(true)
        val jsonObject = JsonObject()
        jsonObject.addProperty("user_type","user")
        jsonObject.addProperty("primary_phone",binding.etPhoneNumber.text.toString())
        jsonObject.addProperty("name",binding.etFullName.text.toString())
        jsonObject.addProperty("area","Mohammadpur")
        jsonObject.addProperty("address",binding.etAddress.text.toString())

        try {
            authViewModel.userSignUp(ApiEndPoint.SIGN_UP,jsonObject)
        }catch (e:Exception){
            onLoadingVm().showLoadingFun(false)
            AppUtils.showToast(requireContext(),
                               Constant.ERROR_MESSAGE, false, getString(R.string.toast_type_error))
        }
    }

    private fun validateUserInputs():String {
        return if(binding.etFullName.text.length <3){
            getString(R.string.please_enter_your_full_name)
        }else if(binding.etPhoneNumber.text.length != 11){
            getString(R.string.please_enter_valid_phone_number)
        }else if(binding.etAddress.text.length < 4){
            getString(R.string.please_enter_your_valid_address)
        }else{
            "ok"
        }
    }
}