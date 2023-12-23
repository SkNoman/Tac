package com.etac.service.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSplashBinding
import com.etac.service.dialogs.AppUpdateDialog
import com.etac.service.dialogs.OnClickListener
import com.etac.service.network.ApiEndPoint
import com.etac.service.network.ApiInterface
import com.etac.service.shared_preference.SharedPref
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>
    (FragmentSplashBinding::inflate),OnClickListener
{
    private val tAG = "SPLASH"
    private val authViewModel: AuthViewModel by viewModels()
    //private lateinit var dialog: DialogFragment

    override fun onResume() {
        super.onResume()
        CheckNetworkStatus.isOnline(requireContext(),object :CheckNetworkStatus.Status{
            override fun online() {
                //getApplicationStatus()
            }
            override fun offline() {
                AppUtils.showToast(requireContext(),getString(R.string.pls_check_internet),
                                   false,getString(R.string.toast_type_error))
            }
        })
    }

  /*  override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val savedUserInfo = SharedPref(requireContext()).getUserInfo()
        if (savedUserInfo != null){
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
        }

       /* authViewModel.applicationStatusRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0){
                    if (it.result?.current_version!! > Constant.CURRENT_BUILD_VERSION)
                    {
                        if (it.result.force_update!!){
                           *//* dialog = AppUpdateDialog(this)
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = false*//*
                        }else{
                          *//*  dialog = AppUpdateDialog(this)
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = true*//*
                        }
                    }
                }
            }
        }

        authViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            error.getContentIfNotHandled().let {
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false,
                                   getString(R.string.toast_type_error))
            }
        }*/
    }

    private fun getApplicationStatus() {
        try {
            authViewModel.getApplicationStatus(ApiEndPoint.GET_APPLICATION_STATUS)
        }catch (e:Exception){
            Toast.makeText(requireContext(),e.message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickUpdate() {
        //dialog.dismiss()
    }
}