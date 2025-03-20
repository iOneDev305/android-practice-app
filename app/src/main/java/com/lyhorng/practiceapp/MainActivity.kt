package com.lyhorng.practiceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lyhorng.practiceapp.databinding.ActivityMainBinding
import com.lyhorng.practiceapp.ui.fragment.Tab1Fragment
import com.lyhorng.practiceapp.ui.fragment.Tab2Fragment
import com.lyhorng.practiceapp.ui.fragment.Tab3Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val adapter = TabPagerAdapter(supportFragmentManager)
//        binding.viewPager.adapter = adapter
//        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    inner class TabPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> Tab1Fragment()
                1 -> Tab2Fragment()
                2 -> Tab3Fragment()
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Tab 1"
                1 -> "Tab 2"
                2 -> "Tab 3"
                else -> null
            }
        }
    }
}