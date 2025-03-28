package com.lyhorng.practiceapp.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lyhorng.practiceapp.common.SessionManager
import com.lyhorng.practiceapp.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example: When the user successfully logs in (e.g., after an API call)
        val token = "user_token_example" // Replace with actual token
        SessionManager.saveSessionData(this, token)

        // Optionally, show a success message
        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
    }
}