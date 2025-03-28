package com.lyhorng.practiceapp.common

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "user_session"
    private const val KEY_DATA = "data" // Token or other session data
    private const val KEY_LOGIN_TIME = "login_time"

    // Save session data and login time
    fun saveSessionData(context: Context, data: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_DATA, data) // Store token or session info
            putLong(KEY_LOGIN_TIME, System.currentTimeMillis()) // Store login time
            apply()
        }
    }

    // Get stored session data (token or other info)
    fun getSessionData(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_DATA, null) // Return null if no data is found
    }

    // Get stored login time
    fun getLoginTime(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getLong(KEY_LOGIN_TIME, 0) // Return 0 if no login time is found
    }

    // Clear stored session data (logout)
    fun clearSessionData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove(KEY_DATA)
            remove(KEY_LOGIN_TIME)
            apply()
        }
    }
}
