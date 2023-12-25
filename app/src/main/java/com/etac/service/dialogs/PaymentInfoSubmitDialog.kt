package com.etac.service.dialogs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.etac.service.R
import com.etac.service.adapters.CustomDropdownAdapter
import com.etac.service.databinding.PaymentInfoSubmitDialogBinding
import com.etac.service.utils.AppUtils
import com.google.gson.JsonObject


class PaymentInfoSubmitDialog(
    serviceId: Int,
    context: Context,
    listener: OnClickListener
): AlertDialog(context, R.style.MaterialAlertDialog_Rounded) {
    private val binding = PaymentInfoSubmitDialogBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        binding.apply {
            tvSubmit.setOnClickListener {
                val paymentMethod: String
                val jsonObj = JsonObject().apply {
                    addProperty("id", serviceId)
                }
                when {
                    binding.radioCash.isChecked -> {
                        paymentMethod = "Cash"
                    }
                    binding.radioOnline.isChecked -> {
                        paymentMethod = binding.etPaymentType.text.toString()
                        if (binding.etPaymentType.text.isEmpty()){
                            AppUtils.showToast(context, "Please select payment method", false,"warning")
                            return@setOnClickListener  // Stop further execution
                        }
                        else if (binding.etTransactionId.text.trim().isEmpty()) {
                            AppUtils.showToast(context, "Please enter transaction id", false, "warning")
                            return@setOnClickListener  // Stop further execution
                        }
                        jsonObj.addProperty("transaction_id", binding.etTransactionId.text.trim().toString())
                    }
                    else -> {
                        // Handle the case where neither radioCash nor radioOnline is checked
                        AppUtils.showToast(context, "Please select payment type", false, "Warning")
                        return@setOnClickListener  // Stop further execution
                    }
                }

                jsonObj.addProperty("payment_method", paymentMethod)
                Log.e("nlog-json-obj",paymentMethod)
                dismiss()
                listener.onClickSubmit(jsonObj)

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
        fun onClickSubmit(jsonObject: JsonObject)
        fun onClickCancel()
    }
}