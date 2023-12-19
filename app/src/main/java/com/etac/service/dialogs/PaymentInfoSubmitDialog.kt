package com.etac.service.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.etac.service.R
import com.etac.service.databinding.PaymentInfoSubmitDialogBinding


class PaymentInfoSubmitDialog(
    context: Context,
    listener: OnClickListener
): AlertDialog(context, R.style.MaterialAlertDialog_Rounded) {

    private val binding = PaymentInfoSubmitDialogBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        binding.tvSubmit.setOnClickListener {
            dismiss()
            listener.onClickSubmit()
        }
        binding.ivExit.setOnClickListener{
            dismiss()
            listener.onClickCancel()
        }
        binding.radioCash.setOnClickListener{
            binding.radioOnline.isChecked = false
            binding.etTransactionId.visibility = View.INVISIBLE
            binding.txtTransactionId.visibility = View.INVISIBLE
        }
        binding.radioOnline.setOnClickListener {
            binding.radioCash.isChecked = false
            binding.etTransactionId.visibility = View.VISIBLE
            binding.txtTransactionId.visibility = View.VISIBLE
        }

        binding.apply {

        }

        setView(binding.root)
    }

    interface OnClickListener {
        fun onClickSubmit()
        fun onClickCancel()
    }
}