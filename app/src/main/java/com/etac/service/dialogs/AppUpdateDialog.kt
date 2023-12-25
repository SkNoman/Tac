package com.etac.service.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.etac.service.R
import com.etac.service.databinding.AlertDialogCustomBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AppUpdateDialog(private val title: String,private val msg: String, listener: OnClickListener): AppCompatDialogFragment() {

    private var binding: AlertDialogCustomBinding? = null
    private var onClickListener = listener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = AlertDialogCustomBinding.inflate(LayoutInflater.from(context))
        binding?.apply {
            titleText.text = title
            messageText.text = msg
            okButton.setOnClickListener{
                dialog?.dismiss()
                onClickListener.onClickUpdate()
            }
        }
        val builder=  context?.let { MaterialAlertDialogBuilder(it, R.style.MaterialAlertDialog_Rounded) }
        builder?.background = ColorDrawable(Color.TRANSPARENT)
        builder?.setView(binding!!.root)
        return builder?.create()!!

    }
}
    interface OnClickListener{
        fun onClickUpdate()
    }