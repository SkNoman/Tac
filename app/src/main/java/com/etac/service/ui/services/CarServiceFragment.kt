package com.etac.service.ui.services

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentCarServiceBinding

class CarServiceFragment : BaseFragmentWithBinding<FragmentCarServiceBinding>(
        FragmentCarServiceBinding::inflate)
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment)
        }
    }
}