package com.etac.service.ui.services

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentCarServiceBinding
import com.etac.service.utils.Animation

class CarServiceFragment : BaseFragmentWithBinding<FragmentCarServiceBinding>(
        FragmentCarServiceBinding::inflate)
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment , null , Animation.animNav().build())
        }
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext() , "Your request submitted successfully" , Toast.LENGTH_SHORT).show()
        }

        val areaList: List<String> = listOf("Mohammadpur","Shayamoli")
        val arrayAdapter = ArrayAdapter(requireActivity(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,areaList)
        binding.etArea.setAdapter(arrayAdapter)
        binding.etArea.threshold = 1
    }
}