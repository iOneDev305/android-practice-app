package com.lyhorng.practiceapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.lyhorng.practiceapp.data.model.GridItem
import com.lyhorng.practiceapp.ui.qr.QrCodeActivity

class GridAdapter(private val items: List<GridItem>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val title: TextView = itemView.findViewById(R.id.title)
        val linearLayout: LinearLayout = itemView as LinearLayout // Directly cast itemView to LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.icon)
        holder.title.text = item.title

        // Set click listener with animation
//        holder.linearLayout.setOnClickListener {
//            animateClick(holder.linearLayout, position)
//        }

        // Set click listener
        holder.itemView.setOnClickListener {
            if (item.title == "Cash-Code") {
                // Start QR Code activity
                val intent = Intent(holder.itemView.context, QrCodeActivity::class.java)
                intent.putExtra("item_title", item.title)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    private fun animateClick(linearLayout: LinearLayout, position: Int) {
        // Create scale animations
        val scaleX = ObjectAnimator.ofFloat(linearLayout, "scaleX", 1f, 0.95f, 1f)
        val scaleY = ObjectAnimator.ofFloat(linearLayout, "scaleY", 1f, 0.95f, 1f)
        val elevation = ObjectAnimator.ofFloat(linearLayout, "translationZ", 0f, 4f, 0f)

        // Create background color animation (from default to grey and back)
        val backgroundColor = ObjectAnimator.ofArgb(
            linearLayout,
            "backgroundColor",
            Color.TRANSPARENT, // Starting color (default background)
            Color.LTGRAY,      // Grey color when clicked
            Color.TRANSPARENT  // Back to default
        )

        // Set animation durations
        scaleX.duration = 200
        scaleY.duration = 200
        elevation.duration = 200

        // Create an AnimatorSet to run animations together
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, elevation)

        // Start the animation
        animatorSet.start()

        // Optional: Log or handle the click action
        Log.d("GridAdapter", "Item clicked at position: $position")
        // Add your click action here (e.g., navigate to another screen)
    }

    override fun getItemCount(): Int = items.size
}