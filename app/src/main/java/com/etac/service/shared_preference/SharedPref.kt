package com.etac.service.shared_preference


import android.content.Context
import android.content.SharedPreferences
import com.etac.service.models.auth.UserInfo
import com.google.gson.Gson

class SharedPref(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserInfo(userInfo: UserInfo) {
        val userInfoJson = gson.toJson(userInfo)
        sharedPreferences.edit().putString("user_info", userInfoJson).apply()
    }

    fun getUserInfo(): UserInfo? {
        val userInfoJson = sharedPreferences.getString("user_info", null)
        return if (userInfoJson != null) {
            gson.fromJson(userInfoJson, UserInfo::class.java)
        } else {
            null
        }
    }
    fun clearUserInfo() {
        sharedPreferences.edit().remove("user_info").apply()
    }
}
