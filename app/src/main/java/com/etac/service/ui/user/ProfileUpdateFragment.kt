package com.etac.service.ui.user

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentProfileUpdateBinding
import com.etac.service.dialogs.SubmitConfirmDialog
import com.etac.service.models.auth.UserInfo
import com.etac.service.models.service.areaList
import com.etac.service.network.ApiEndPoint
import com.etac.service.shared_preference.SharedPref
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.GeneralViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileUpdateFragment : BaseFragmentWithBinding<FragmentProfileUpdateBinding>(
    FragmentProfileUpdateBinding::inflate)
{
    private val generalViewModel: GeneralViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SET SAVED USER VALUES
        val savedUserInfo = SharedPref(requireContext()).getUserInfo()

        binding.apply {
            etName.setText(savedUserInfo?.name)
            etPhoneNumber.setText(savedUserInfo?.phoneNumber)
            etPhoneNumberAlternative.setText(savedUserInfo?.alternativePhoneNumber)
            etArea.setText(savedUserInfo?.area)
            etAddress.setText(savedUserInfo?.address)
        }

        val arrayAdapter = ArrayAdapter(requireActivity(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, areaList
        )
        binding.etArea.setAdapter(arrayAdapter)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.profileFragment,null,Animation.animNav().build())
        }
        binding.btnSubmit.setOnClickListener {
            CheckNetworkStatus.isOnline(requireContext(), object: CheckNetworkStatus.Status{
                override fun online() {
                    if (userInputValidation() == "ok"){
                        SubmitConfirmDialog(requireContext(),object:SubmitConfirmDialog.OnClickListener{
                            override fun onClickPositive() {
                                updateUserInfo()
                            }
                            override fun onClickNegative() {}
                        }).show()

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

        generalViewModel.updateUserInfoRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0) {
                    onLoadingVm().showLoadingFun(false)
                    //SharedPref(requireContext()).clearUserInfo()
                    val userInfo = UserInfo(
                            binding.etName.text.toString(),
                            binding.etPhoneNumber.text.toString(),
                            binding.etPhoneNumberAlternative.text.toString(),
                            binding.etArea.text.toString(),
                            binding.etAddress.text.toString()
                    )
                    SharedPref(requireContext()).saveUserInfo(userInfo)
                    AppUtils.showToast(requireContext(),
                                       it.result?.message.toString(), true, getString(R.string.toast_type_success))
                    findNavController().navigate(R.id.profileFragment,null,Animation.animNav().build())
                }
            }
        }
        generalViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            onLoadingVm().showLoadingFun(false)
            error.getContentIfNotHandled().let {
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false, getString(R.string.toast_type_error))
            }
        }
    }

    private fun updateUserInfo() {
        onLoadingVm().showLoadingFun(true)
        try {
            val jsonObject = JsonObject()
            jsonObject.addProperty("user_type",Constant.USER_TYPE)
            jsonObject.addProperty("name",binding.etName.text.toString())
            jsonObject.addProperty("primary_phone",binding.etPhoneNumber.text.toString())
            jsonObject.addProperty("alternative_phone",binding.etPhoneNumberAlternative.text.toString())
            jsonObject.addProperty("area",binding.etArea.text.toString())
            jsonObject.addProperty("address",binding.etAddress.text.toString())

            generalViewModel.updateUserInfo(ApiEndPoint.UPDATE_USER_INFO, jsonObject)

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