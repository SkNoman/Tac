package com.etac.service.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSplashBinding
import com.etac.service.utils.Animation

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>
    (FragmentSplashBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
        }
    }
}