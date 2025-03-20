package com.lyhorng.practiceapp

import com.lyhorng.practiceapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the card layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_outlined, parent, false)

        // Get the screen width
        val displayMetrics = parent.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels

        // Set the item width to be a fraction of the screen width (for 1.5 items, use ~66% of the screen width)
        val itemWidth = (screenWidth * 0.66).toInt() // or 0.50 for 50% width
        val params = view.layoutParams as RecyclerView.LayoutParams
        params.width = itemWidth
        view.layoutParams = params

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to the item here
    }

    override fun getItemCount(): Int {
        return 4 // Number of items in RecyclerView
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

