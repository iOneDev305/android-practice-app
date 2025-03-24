package com.lyhorng.practiceapp.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val LANGUAGE_KEY = "language"
    }

    fun saveLanguage(languageCode: String) {
        val editor = sharedPreferences.edit()
        editor.putString(LANGUAGE_KEY, languageCode)
        editor.apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY, "en") ?: "en"
    }
}