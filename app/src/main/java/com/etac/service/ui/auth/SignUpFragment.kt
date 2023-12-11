package com.etac.service.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSignUpBinding
import com.etac.service.utils.Animation

class SignUpFragment : BaseFragmentWithBinding<FragmentSignUpBinding>
    (FragmentSignUpBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.layoutNavigateSignIn.setOnClickListener{
            findNavController().navigate(R.id.signInFragment , null , Animation.animNav().build())
        }
    }
}