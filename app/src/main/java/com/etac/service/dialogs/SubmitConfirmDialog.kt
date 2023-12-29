package com.etac.service.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.etac.service.R
import com.etac.service.databinding.DialogConfirmationBinding

class SubmitConfirmDialog(context: Context, listener: OnClickListener):
    AlertDialog(context, R.style.MaterialAlertDialog_Rounded) {

    private val binding = DialogConfirmationBinding.
    inflate(LayoutInflater.from(context), null, false)

    init {
        binding.btnPositive.setOnClickListener {
            dismiss()
            listener.onClickPositive()
        }
        binding.btnNegative.setOnClickListener {
            dismiss()
            listener.onClickNegative()
        }
        setView(binding.root)
    }

    interface OnClickListener {
        fun onClickPositive()
        fun onClickNegative()
    }
}