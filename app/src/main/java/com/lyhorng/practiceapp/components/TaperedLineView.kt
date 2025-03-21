package com.lyhorng.practiceapp.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TaperedLineView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint: Paint = Paint()
    private val path: Path = Path()

    // Variables to control start and end points
    private var startX: Float = 0f
    private var startY: Float = 0f
    private var endX: Float = 0f
    private var endY: Float = 0f

    init {
        paint.color = Color.WHITE  // Line color set to white to match the grid
        paint.strokeWidth = 2f  // Slightly thicker line to match the image (previously 1dp)
        paint.style = Paint.Style.STROKE

        // Apply dashed effect
        val intervals = floatArrayOf(10f, 5f)  // Dash length and gap (matches the original TaperedLineView)
        val effect = DashPathEffect(intervals, 0f)
        paint.pathEffect = effect
    }

    // Public method to set the line's start and end points
    fun setLinePoints(startX: Float, startY: Float, endX: Float, endY: Float) {
        this.startX = startX
        this.startY = startY
        this.endX = endX
        this.endY = endY
        path.reset()  // Clear the previous path
        path.moveTo(startX, startY)  // Set start point
        path.lineTo(endX, endY)  // Set end point
        invalidate()  // Request redraw
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }
}