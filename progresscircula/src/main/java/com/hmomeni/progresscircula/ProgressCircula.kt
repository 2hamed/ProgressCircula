package com.hmomeni.progresscircula

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


class ProgressCircula(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {
    private val TAG = this.javaClass.simpleName!!

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(context, attributeSet, 0) {
        val a = context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.ProgressCircula,
                0, 0)

        try {
            progress = a.getInteger(R.styleable.ProgressCircula_progress, progress)
            showProgress = a.getBoolean(R.styleable.ProgressCircula_showProgress, showProgress)
            indeterminate = a.getBoolean(R.styleable.ProgressCircula_indeterminate, indeterminate)
            rimColor = a.getInteger(R.styleable.ProgressCircula_rimColor, rimColor)
            rimWidth = a.getDimension(R.styleable.ProgressCircula_rimWidth, rimWidth)
            textColor = a.getInteger(R.styleable.ProgressCircula_textColor, textColor)
        } finally {
            a.recycle()
        }

    }

    private val oval = RectF()
    private val textBounds = Rect()
    private var step = 0
    private var isRotating = true
    private var currentProgress = 0
    var progress = 0
        set(value) {
            field = value
            indeterminate = false
            if (value < 100) {
                isRotating = true
                postInvalidate()
            }
        }

    var indeterminate = true
        set(value) {
            field = value
            if (value) {
                showProgress = false
                isRotating = true
                postInvalidate()
            }
        }
    var showProgress = true
        set(value) {
            field = value
            postInvalidate()
        }
    var textColor = Color.BLACK
        set(value) {
            field = value
            textPaint.color = value
        }

    var rimColor = Color.RED
        set(value) {
            field = value
            outerRim.color = value
        }

    var rimWidth = dpToPx(3).toFloat()
        set(value) {
            field = value
            outerRim.strokeWidth = value
        }

    private val outerRim = Paint().apply {
        color = rimColor
        strokeWidth = rimWidth
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = textColor
        textAlign = Paint.Align.CENTER
        textSize = dpToPx(16).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        step += 2

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
        calculateStartAngle()
        calculateSweepAngle()
        canvas.drawArc(oval, startAngle, sweepAngle, false, outerRim)
        if (isRotating)
            postInvalidateDelayed(10)
        if (step >= 360) {
            step = 0
        }

        if (showProgress) {
            val text = "$currentProgress%"
            textPaint.getTextBounds(text, 0, text.length, textBounds)
            canvas.drawText(text, centerX, centerY - textBounds.exactCenterY(), textPaint)
        }
    }

    private var isIncrement = true
    private var sweepAngle: Float = 0f

    private fun calculateSweepAngle() {
        if (!indeterminate) {
            if (currentProgress < progress) {
                currentProgress++
            } else if (currentProgress > progress) {
                currentProgress--
            }
            if (currentProgress >= 100) {
                isRotating = false
            }
            sweepAngle = currentProgress * 360 / 100F
        } else {
            if (isIncrement) {
                currentProgress++
                sweepAngle += 4
            } else {
                currentProgress--
                sweepAngle -= 4
            }
            /*if (currentProgress >= 100) {
                isIncrement = false
            } else if (currentProgress <= 0) {
                isIncrement = true
            }*/
            if (sweepAngle >= 360) {
                isIncrement = false
            } else if (sweepAngle <= 0) {
                isIncrement = true
            }
        }
//        Log.d(TAG, "sweepAngle: $sweepAngle")

    }

    private fun calculateStartAngle() {
        if (!indeterminate) {
            startAngle = step % 360F
        } else {
            startAngle += if (!isIncrement) {
                8
            }else{
                4
            }
        }
        startAngle %= 360f
//        Log.d(TAG, "startAngle: $startAngle")
    }

    private var startAngle: Float = 0f

    fun startRotation() {
        isRotating = true
        postInvalidate()
    }

    fun stopRotation() {
        isRotating = false
    }
}