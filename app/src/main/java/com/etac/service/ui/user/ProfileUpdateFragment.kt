package com.etac.service.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentProfileUpdateBinding
import com.etac.service.utils.Animation


class ProfileUpdateFragment : BaseFragmentWithBinding<FragmentProfileUpdateBinding>(
    FragmentProfileUpdateBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.profileFragment,null,Animation.animNav().build())
        }
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext() , "Your profile updated successfully" , Toast.LENGTH_SHORT).show()
        }
    }
}