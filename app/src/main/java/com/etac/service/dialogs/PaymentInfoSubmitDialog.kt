package com.etac.service.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.etac.service.R
import com.etac.service.adapters.CustomDropdownAdapter
import com.etac.service.databinding.PaymentInfoSubmitDialogBinding
import com.etac.service.models.service.areaList
import com.etac.service.utils.HideKeyboard.hideKeyboard


class PaymentInfoSubmitDialog(
    context: Context,
    listener: OnClickListener
): AlertDialog(context, R.style.MaterialAlertDialog_Rounded) {

    private val binding = PaymentInfoSubmitDialogBinding.inflate(LayoutInflater.from(context), null, false)

    init {

        binding.apply {
            tvSubmit.setOnClickListener {
                dismiss()
                listener.onClickSubmit()
            }
           ivExit.setOnClickListener{
                dismiss()
                listener.onClickCancel()
            }
            val paymentInfoList: List<String> = listOf("bKash","Nagad","Rocket","NexusPay")
            val adapter = CustomDropdownAdapter(context,
               androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, paymentInfoList
            )
            etPaymentType.setAdapter(adapter)
            etPaymentType.setOnClickListener {
                binding.etPaymentType.showDropDown()
            }

            radioCash.setOnClickListener{
                radioOnline.isChecked = false
                group.visibility = View.GONE
            }
            radioOnline.setOnClickListener {
                radioCash.isChecked = false
                group.visibility = View.VISIBLE
            }
        }

        setView(binding.root)
    }

    interface OnClickListener {
        fun onClickSubmit()
        fun onClickCancel()
    }
}