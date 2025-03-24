package com.lyhorng.practiceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lyhorng.practiceapp.common.LanguageUtils
import com.lyhorng.practiceapp.common.SharedPreferencesHelper
import com.lyhorng.practiceapp.databinding.ActivityMainBinding
import com.lyhorng.practiceapp.ui.demo.DemoActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // Set language based on saved preferences
        setLanguageFromPreferences()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextDemo.setOnClickListener{
            val intent = Intent(this, DemoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setLanguageFromPreferences() {
        // Get the saved language preference and apply it
        val languageCode = sharedPreferencesHelper.getLanguage()
        LanguageUtils.setLocale(this, languageCode)
    }
}
