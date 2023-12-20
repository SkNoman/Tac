package com.etac.service.activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.etac.service.R


open class BaseActivity : AppCompatActivity() {
    private lateinit var dialog: AlertDialog


    fun initAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog, null)
        dialog = AlertDialog.Builder(this, R.style.CustomDialog)
            .setView(dialogView).setCancelable(false)
            .create()
    }
    fun showDialog() {
        dialog.show()
    }
    fun dismissDialog() {
        dialog.dismiss()
    }
}