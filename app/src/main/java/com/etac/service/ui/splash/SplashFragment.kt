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
import com.etac.service.utils.Animation
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
    lateinit var dialog: DialogFragment

    override fun onResume() {
        super.onResume()
        CheckNetworkStatus.isOnline(requireContext(),object :CheckNetworkStatus.Status{
            override fun online() {
                getApplicationStatus()
            }
            override fun offline() {
                Toast.makeText(requireContext(),getString(R.string.pls_check_internet),Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
        }


        authViewModel.applicationStatusRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                if (it?.result_code == 0){
                    Log.d(tAG,"Application Status Response: $it")
                    if (it.result?.current_version!! > Constant.CURRENT_BUILD_VERSION){
                        if (it.result.force_update!!){
                            dialog = AppUpdateDialog(this)
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = false
                        }else{
                            dialog = AppUpdateDialog(this)
                            dialog.show(childFragmentManager, "Update App Dialog")
                            dialog.isCancelable = true
                        }
                    }
                    //Toast.makeText(requireContext(), it.result.current_version.toString(), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),Constant.ERROR_MESSAGE,Toast.LENGTH_SHORT).show()
                }
            }
        }

        authViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            error.getContentIfNotHandled().let {
                Toast.makeText(requireContext(),it?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getApplicationStatus() {
        try {
            authViewModel.getApplicationStatus(ApiEndPoint.GET_APPLICATION_STATUS)
        }catch (e:Exception){
            Log.d(tAG,"getApplicationStatusErr:$e")
        }
    }

    override fun onClickUpdate() {
        dialog.dismiss()
    }
}