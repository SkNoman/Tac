package com.etac.service.utils

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.etac.service.R
import com.etac.service.utils.MotionToast.MotionToastCustom
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object AppUtils {
    fun showToast(context: Context, message: String, isLong: Boolean, toastType: String) {
        MotionToastCustom.darkColorToast(
                context,
                when (toastType) {
                    context.getString(R.string.toast_type_success) -> "Success"
                    context.getString(R.string.toast_type_error) -> "Error"
                    context.getString(R.string.toast_type_warning) -> "Warning"
                    else -> ""
                },
                message,
                when (toastType) {
                    context.getString(R.string.toast_type_success) -> MotionToastStyle.SUCCESS
                    context.getString(R.string.toast_type_error) -> MotionToastStyle.ERROR
                    context.getString(R.string.toast_type_warning) -> MotionToastStyle.WARNING
                    else -> MotionToastStyle.SUCCESS
                },
                MotionToast.GRAVITY_BOTTOM,
                if (isLong) MotionToast.LONG_DURATION else MotionToast.SHORT_DURATION,
                ResourcesCompat.getFont(context, R.font.open_sans)
        )
    }
}