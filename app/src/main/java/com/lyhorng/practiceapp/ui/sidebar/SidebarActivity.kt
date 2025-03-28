package com.lyhorng.practiceapp.ui.sidebar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.lyhorng.practiceapp.R
import com.lyhorng.practiceapp.common.LanguageUtils
import com.lyhorng.practiceapp.common.SharedPreferencesHelper
import com.lyhorng.practiceapp.databinding.ActivitySidebarBinding


class SidebarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySidebarBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // Set language based on saved preferences
        setLanguageFromPreferences()

        // Inflate the layout using ViewBinding
        binding = ActivitySidebarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up the toolbar
        setSupportActionBar(binding.topAppBar)

        // Open the drawer when the menu icon is clicked
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        // Handle navigation item clicks
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                   Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun setLanguageFromPreferences() {
        // Get the saved language preference and apply it
        val languageCode = sharedPreferencesHelper.getLanguage()
        LanguageUtils.setLocale(this, languageCode)
    }
}
