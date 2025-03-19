package com.lyhorng.practiceapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyhorng.practiceapp.data.model.GridItem
import com.lyhorng.practiceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView // Ensure you have a RecyclerView in activity_main.xml
        recyclerView.layoutManager = GridLayoutManager(this, 3) // 3 columns

        // Sample data
        val items = listOf(
            GridItem(R.drawable.ic_wallet, "Accounts"),
            GridItem(R.drawable.ic_card, "Cards"),
            GridItem(R.drawable.ic_wallet, "Deposits"),
            GridItem(R.drawable.ic_wallet, "Loans"),
            GridItem(R.drawable.ic_wallet, "Cash-Code"),
            GridItem(R.drawable.ic_wallet, "Top-Up"),
        )

        // Set adapter
        recyclerView.adapter = GridAdapter(items)

        // Add dividers
        recyclerView.addItemDecoration(GridDividerItemDecoration(this))
    }
}