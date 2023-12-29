package com.etac.service.ui.general

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentAboutUsBinding
import com.etac.service.utils.Animation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : BaseFragmentWithBinding<FragmentAboutUsBinding>
(FragmentAboutUsBinding::inflate)
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.layoutGoogleMaps.setOnClickListener{

            val latitude = 23.7584892 // Replace with the actual latitude
            val longitude = 90.3636594 // Replace with the actual longitude

            val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude"
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(geoUri))
            intent.setPackage("com.google.android.apps.maps")
            requireActivity().startActivity(intent)
        }

        binding.contactUsPhoneNumber.setOnClickListener {
            val phoneNumber = "+8801718228277" // Replace with the actual phone number
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            // Check if there is an app to handle the dial intent
            if (dialIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(dialIntent)
            }
        }

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }
    }
}