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
    var isRotating = true
    var progress = 0
    private var currentProgress = 0


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        step += 3

        val width = width.toFloat()
        val height = height.toFloat()
        val radius: Float

        radius = if (width > height) {
            height / 2
        } else {
            width / 2
        } - paddingBottom

        val centerX = width / 2
        val centerY = height / 2

        oval.set(centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius)
        canvas.drawArc(oval, step % 360F, calculateSweepAngle(), false, outerRim)
        if (isRotating)
            postInvalidateDelayed(10)
        if (step >= 360) {
            step = 0
        }
    }

    private fun calculateSweepAngle(): Float {
        if (currentProgress < progress) {
            currentProgress++
        } else if (currentProgress > progress) {
            currentProgress--
        }

        /*if (currentProgress >= 100) {
            isRotating = false
        }*/

        return currentProgress * 360 / 100F
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