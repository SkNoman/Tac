package com.etac.service.ui.splash

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.etac.service.BuildConfig
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSplashBinding
import com.etac.service.dialogs.AppUpdateDialog
import com.etac.service.dialogs.OnClickListener
import com.etac.service.network.ApiEndPoint
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
    //private val tAG = "SPLASH"
    private val authViewModel: AuthViewModel by viewModels()
    private var isShowingAppDialog = false
    private var dialog = DialogFragment()

    override fun onResume() {
        super.onResume()
        CheckNetworkStatus.isOnline(requireContext(),object :CheckNetworkStatus.Status{
            override fun online() {
                getApplicationStatus()
            }
            override fun offline() {
                AppUtils.showToast(requireContext(),getString(R.string.pls_check_internet),
                false,getString(R.string.toast_type_warning))
            }
        })
    }

    override fun onPause() {
        super.onPause()
        if (isShowingAppDialog) {
            dialog.dismiss()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val savedUserInfo = SharedPref(requireContext()).getUserInfo()
        if (savedUserInfo != null){
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
        }

        binding.tvAppVersion.text = buildString {
            append("Version: ")
            append(BuildConfig.VERSION_NAME)
            append(".")
            append(BuildConfig.VERSION_CODE)
        }

        authViewModel.applicationStatusRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                onLoadingVm().showLoadingFun(false)
                if (it?.result_code == 0){
                    if (it.result?.current_version!! > BuildConfig.VERSION_CODE)
                    {
                        if (it.result.force_update!!){
                            dialog = AppUpdateDialog(it.result.title,it.result.message,this)
                            isShowingAppDialog = true
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = false
                        }else{
                            dialog = AppUpdateDialog(it.result.title,it.result.message,this)
                            isShowingAppDialog = true
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = true
                            binding.btnContinue.visibility = View.VISIBLE
                        }
                    }
                    else if (it.result.current_version == BuildConfig.VERSION_CODE){
                        binding.btnContinue.visibility = View.VISIBLE
                    }
                }
            }
        }

        authViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            error.getContentIfNotHandled().let {
                onLoadingVm().showLoadingFun(false)
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false,
                                   getString(R.string.toast_type_error))
            }
        }
    }

    private fun getApplicationStatus() {
        try {
            onLoadingVm().showLoadingFun(true)
            authViewModel.getApplicationStatus(ApiEndPoint.GET_APPLICATION_STATUS)
        }catch (e:Exception){
            onLoadingVm().showLoadingFun(false)
            AppUtils.showToast(requireContext(),e.message.toString(),false,getString(R.string.toast_type_error))
        }
    }

    override fun onClickUpdate() {
        dialog.dismiss()
        val appId = BuildConfig.APPLICATION_ID
        try {
            this.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appId")
                    )
            )
        } catch (e: ActivityNotFoundException) {
            this.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(Constant.CLIENT_UPDATE_URL)
                    )
            )
        }
    }
}