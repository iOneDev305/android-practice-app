package com.lyhorng.practiceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lyhorng.practiceapp.common.LanguageUtils
import com.lyhorng.practiceapp.common.SessionManager
import com.lyhorng.practiceapp.common.SharedPreferencesHelper
import com.lyhorng.practiceapp.databinding.ActivityMainBinding
import com.lyhorng.practiceapp.ui.login.LoginActivity  // Ensure you import the correct LoginActivity package

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferencesHelper to manage preferences (like language)
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        setLanguageFromPreferences()

        // Inflate the layout and set content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user has a valid session
        checkLoginSession()

        // Show token for debugging purposes
        val token = SessionManager.getSessionData(this)
        Toast.makeText(this, "Token: $token", Toast.LENGTH_SHORT).show()
    }

    // Method to check the login session and expiration
    private fun checkLoginSession() {
        val sessionData = SessionManager.getSessionData(this)
        val loginTime = SessionManager.getLoginTime(this)

        if (sessionData != null && loginTime != 0L) {
            val currentTime = System.currentTimeMillis()
            // Session expires after 5 minutes (3000000ms)
            if (currentTime - loginTime > 3000000) { // Adjust this time as needed
                // Session expired, clear session and redirect to login
                SessionManager.clearSessionData(this)
                redirectToLogin()
            } else {
                // Show token in Toast (Can be replaced with Snackbar for better UX)
                Toast.makeText(this, "Session is valid. Token: $sessionData", Toast.LENGTH_SHORT).show()
            }
        } else {
            // No session data, redirect to login
            redirectToLogin()
        }
    }

    // Redirect to LoginActivity if session is expired or does not exist
    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        // Ensures LoginActivity is the new task and clears previous activities from the stack
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Close the MainActivity after redirecting to login
    }

    // Set the language of the app based on preferences stored in SharedPreferences
    private fun setLanguageFromPreferences() {
        val languageCode = sharedPreferencesHelper.getLanguage()  // Fetch language preference
        LanguageUtils.setLocale(this, languageCode)  // Set the locale/language of the app
    }
}
