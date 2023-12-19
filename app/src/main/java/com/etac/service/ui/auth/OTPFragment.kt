package com.etac.service.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentOTPBinding
import com.etac.service.utils.Animation
import com.etac.service.utils.HideKeyboard.hideKeyboard

class OTPFragment: BaseFragmentWithBinding<FragmentOTPBinding>
    (FragmentOTPBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnValidateOTP.setOnClickListener {
            if (binding.layoutOTP.text.toString() == "123456"){
                findNavController().navigate(R.id.dashboardFragment , null , Animation.animNav().build())
            }else {
                // OTP is invalid, you can show an error message
                Toast.makeText(requireContext() , "Invalid OTP" , Toast.LENGTH_SHORT).show()
            }
        }

        binding.layoutOTP.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Check if the OTP is complete
                if (editable?.length == binding.layoutOTP.itemCount) {
                    // Perform your OTP validation here
                    requireActivity().hideKeyboard()
                    val enteredOTP = editable.toString()
                    // For example, you can check if the entered OTP is "123456"
                    if (enteredOTP == "123456") {
                        // OTP is valid, navigate to the next page
                        binding.layoutOTP.setText("")
                        Toast.makeText(requireContext(),"Welcome, Mohammad",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
                    } else {
                        // OTP is invalid, you can show an error message
                        Toast.makeText(requireContext() , "Invalid OTP" , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}