package com.etac.service.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentSignInBinding


class SignInFragment : BaseFragmentWithBinding<FragmentSignInBinding>
    (FragmentSignInBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.OTPFragment)
        }
        binding.tvNavigateSignIn.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }
}