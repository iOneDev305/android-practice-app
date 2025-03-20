package com.lyhorng.practiceapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lyhorng.practiceapp.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment() {
    private var _binding: FragmentTab2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tab2Text.text = "Welcome to Tab 2"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}