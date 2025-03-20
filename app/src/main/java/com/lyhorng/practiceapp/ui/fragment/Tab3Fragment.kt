package com.lyhorng.practiceapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lyhorng.practiceapp.databinding.FragmentTab3Binding

class Tab3Fragment : Fragment() {
    private var _binding: FragmentTab3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTab3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tab3Text.text = "Welcome to Tab 3"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}