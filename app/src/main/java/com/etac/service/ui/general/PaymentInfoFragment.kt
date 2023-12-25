package com.etac.service.ui.general

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentPaymentInfoBinding
import com.etac.service.utils.Animation
import com.etac.service.utils.Constant

class PaymentInfoFragment : BaseFragmentWithBinding<FragmentPaymentInfoBinding>(
        FragmentPaymentInfoBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }
        setPaymentNumbers()

        binding.apply {

            ivBkashCopy.setOnClickListener {
                setDataToClipBoard(Constant.BKASH_NUMBER)
            }
            ivNagadCopy.setOnClickListener {
                setDataToClipBoard(Constant.NAGAD_NUMBR)
            }
            ivRocketCopy.setOnClickListener {
                setDataToClipBoard(Constant.ROKET_NUMBER)
            }
            ivNexusCopy.setOnClickListener {
                setDataToClipBoard(Constant.NEXUS_PAY_AC_NUMBER)
            }
        }


    }

    private fun setDataToClipBoard(copyText: String) {

        val clipboardManager = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Uni Trust Payment AC No", copyText)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(requireContext(), "AC No: $copyText copied", Toast.LENGTH_SHORT).show()
    }

    private fun setPaymentNumbers() {
        binding.txtBkashNumber.text = buildString {
            append("Number: ")
            append(Constant.BKASH_NUMBER)
        }
        binding.txtNagadNumber.text = buildString {
            append("Number: ")
            append(Constant.NAGAD_NUMBR)
        }
        binding.txtTakaNumber.text = buildString {
            append("Number: ")
            append(Constant.ROKET_NUMBER)
        }
        binding.txtNexusNumber.text = buildString {
            append("AC No: ")
            append(Constant.NEXUS_PAY_AC_NUMBER)
        }

    }

}