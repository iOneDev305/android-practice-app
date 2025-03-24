package com.lyhorng.practiceapp.common

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageUtils {

    fun setLocale(context: Context, languageCode: String) {
        // Update locale
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}