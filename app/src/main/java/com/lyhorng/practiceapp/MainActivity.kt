package com.lyhorng.practiceapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lyhorng.practiceapp.ui.demo.DemoActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the language based on saved preference before setting content view
        setLanguageFromPreferences()

        setContentView(R.layout.activity_main)

        val currentLanguage = resources.configuration.locales.get(0).language

        // Switch language button
        findViewById<Button>(R.id.switchLanguageButton).setOnClickListener {
            // Toggle between English and Khmer
            val newLanguage = if (currentLanguage == "km") "en" else "km"
            changeLanguage(newLanguage)

            // Restart activity to apply the language change

            finish()  // Finish the current activity to avoid pressing back
        }

        findViewById<Button>(R.id.nextDemo).setOnClickListener {
            startActivity(Intent(this, DemoActivity::class.java))
        }
    }

    private fun changeLanguage(languageCode: String) {
        // Save the new language to SharedPreferences
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("language", languageCode)
        editor.apply()

        // Update the locale
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Recreate the activity to apply the new language
        recreate()
    }

    private fun setLanguageFromPreferences() {
        // Get the saved language preference (default to English if not set)
        val sharedPreferences: SharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val languageCode = sharedPreferences.getString("language", "en") ?: "en"

        // Set the locale to the saved language
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
