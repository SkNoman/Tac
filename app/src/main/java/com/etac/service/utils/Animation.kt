package com.etac.service.utils

import androidx.navigation.NavOptions
import com.etac.service.R

object Animation {
        fun animNav(): NavOptions.Builder{
            val navBuilder = NavOptions.Builder()
            navBuilder.setEnterAnim(R.anim.enter_right_to_left)
                .setExitAnim(R.anim.exit_left_to_right)
                .setPopEnterAnim(R.anim.enter_left_to_right)
                .setPopExitAnim(R.anim.exit_right_to_left)
            return navBuilder
        }
}