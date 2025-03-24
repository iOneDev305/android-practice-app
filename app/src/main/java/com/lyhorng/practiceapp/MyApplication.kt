package com.lyhorng.practiceapp

import android.app.Application
import android.content.res.Configuration
import java.util.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Apply the current language and theme globally
        val currentLanguage = getCurrentLanguage()
        setLocale(currentLanguage)
        applyTheme(currentLanguage)
    }

    private fun getCurrentLanguage(): String {
        // Get the saved language preference or default to system language
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        return sharedPreferences.getString("language", Locale.getDefault().language) ?: Locale.getDefault().language
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun applyTheme(languageCode: String) {
        // You can apply the appropriate theme based on the language
        if (languageCode == "km") {
            // Apply Khmer theme
            setTheme(R.style.AppTheme_Khmer)
        } else {
            // Apply default (English) theme
            setTheme(R.style.AppTheme)
        }
    }
}
