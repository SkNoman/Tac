package com.etac.service.ui.general

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentAboutUsBinding
import com.etac.service.utils.Animation

class AboutUsFragment : BaseFragmentWithBinding<FragmentAboutUsBinding>
(FragmentAboutUsBinding::inflate)
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.ivGoogleMapView.setOnClickListener{
            val uri =  "geo: 20.999,90.232?q=Tokyo Square, Mohammadpur,Dhaka ,Bangladesh"
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            requireActivity().startActivity(intent)
        }

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }
    }
}