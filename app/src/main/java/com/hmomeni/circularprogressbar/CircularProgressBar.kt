package com.hmomeni.circularprogressbar

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CircularProgressBar(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(context, attributeSet, 0)

    private val path = Path()
    private val outerRim = Paint().apply {
        color = Color.RED
        strokeWidth = dpToPx(3).toFloat()
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }
    private val oval = RectF()

    var step = 0
    var isRotating = false

    private var sweepAngle = 50F
    var targetSweepAngle = sweepAngle

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        step += 5

        val width = width.toFloat()
        val height = height.toFloat()
        val radius: Float

        radius = if (width > height) {
            height / 2
        } else {
            width / 2
        } - paddingBottom

        path.addCircle(width / 2,
                height / 2, radius,
                Path.Direction.CW)
        val center_x: Float
        val center_y: Float


        center_x = width / 2
        center_y = height / 2

        oval.set(center_x - radius,
                center_y - radius,
                center_x + radius,
                center_y + radius)
        canvas.drawArc(oval, step % 360F, calculateSweepAngle(), false, outerRim)
        if (isRotating)
            postInvalidateDelayed(10)
    }

    private fun calculateSweepAngle(): Float {
        if (sweepAngle < targetSweepAngle) {
            sweepAngle++
        }
        return sweepAngle
    }

    fun startRotation() {
        isRotating = true
        postInvalidate()
    }

    fun stopRotation() {
        isRotating = false
    }
}

fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()