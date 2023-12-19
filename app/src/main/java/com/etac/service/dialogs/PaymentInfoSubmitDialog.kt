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


        binding.apply {
            tvSubmit.setOnClickListener {
                dismiss()
                listener.onClickSubmit()
            }
            ivExit.setOnClickListener{
                dismiss()
                listener.onClickCancel()
            }
            radioCash.setOnClickListener{
                radioOnline.isChecked = false
                etTransactionId.visibility = View.INVISIBLE
                txtTransactionId.visibility = View.INVISIBLE
            }
            radioOnline.setOnClickListener {
                radioCash.isChecked = false
                etTransactionId.visibility = View.VISIBLE
                txtTransactionId.visibility = View.VISIBLE
            }
        }

        setView(binding.root)
    }

    interface OnClickListener {
        fun onClickSubmit()
        fun onClickCancel()
    }
}