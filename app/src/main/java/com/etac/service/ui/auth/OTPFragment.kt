package com.etac.service.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentOTPBinding
import com.etac.service.utils.Animation

class OTPFragment: BaseFragmentWithBinding<FragmentOTPBinding>
    (FragmentOTPBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnValidateOTP.setOnClickListener {
            findNavController().navigate(R.id.dashboardFragment , null , Animation.animNav().build())
        }
    }
}