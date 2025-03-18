package com.lyhorng.practiceapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class GridDividerItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    private val divider: Drawable? = ContextCompat.getDrawable(context, android.R.drawable.divider_horizontal_bright)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val divider = divider ?: return
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: return

        // Draw vertical lines
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val position = parent.getChildAdapterPosition(child)

            if ((position + 1) % spanCount != 0) { // Skip the last column
                val left = child.right + params.rightMargin
                val right = left + divider.intrinsicWidth
                val top = child.top - params.topMargin
                val bottom = child.bottom + params.bottomMargin
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }

        // Draw horizontal lines
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val position = parent.getChildAdapterPosition(child)

            if (position < (parent.adapter?.itemCount ?: 0) - spanCount) { // Skip the last row
                val left = child.left - params.leftMargin
                val right = child.right + params.rightMargin
                val top = child.bottom + params.bottomMargin
                val bottom = top + divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

        outRect.right = if ((position + 1) % spanCount != 0) divider?.intrinsicWidth ?: 0 else 0
        outRect.bottom = if (position < (parent.adapter?.itemCount ?: 0) - spanCount) divider?.intrinsicHeight ?: 0 else 0
    }
}