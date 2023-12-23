package com.etac.service.ui.user

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentProfileBinding
import com.etac.service.shared_preference.SharedPref
import com.etac.service.utils.Animation

class ProfileFragment : BaseFragmentWithBinding<FragmentProfileBinding>(
        FragmentProfileBinding::inflate
) {
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val savedUserInfo = SharedPref(requireContext()).getUserInfo()
        if (savedUserInfo != null){
            binding.apply {
                txtUserName.text = savedUserInfo.name
                txtUserPhoneNumber.text = savedUserInfo.phoneNumber
                if (savedUserInfo.alternativePhoneNumber.isNotEmpty()){
                    txtAlternativePhoneNo.visibility = View.VISIBLE
                    txtLabelAlternativePhNo.visibility = View.VISIBLE
                    txtAlternativePhoneNo.text = savedUserInfo.alternativePhoneNumber
                }
                txtUserArea.text = savedUserInfo.area
                txtUserAddress.text = savedUserInfo.address
            }
        }
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment , null , Animation.animNav().build())
        }
        binding.ivEdit.setOnClickListener{
            findNavController().navigate(R.id.profileUpdateFragment,null,Animation.animNav().build())
        }
        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.signInFragment,null,Animation.animNav().build())
        }
    }
}