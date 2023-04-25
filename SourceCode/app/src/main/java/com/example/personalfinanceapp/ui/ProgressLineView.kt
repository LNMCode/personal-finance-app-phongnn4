package com.example.personalfinanceapp.ui

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.personalfinanceapp.R

@SuppressLint("ResourceType")
class ProgressLineView @JvmOverloads constructor(

    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private var bgColor: Int = Color.parseColor("#DAF5F4")
    private var textColor: Int = Color.parseColor("#4873A6") // default color

    // tells the compiler that the value of a variable
    // must never be cached as its value may change outside
    @Volatile
    private var progress: Double = 0.0
    private var valueAnimator: ValueAnimator


    // It updates the percentages
    private val updateListener = ValueAnimator.AnimatorUpdateListener {
        progress = (it.animatedValue as Float).toDouble()
        invalidate()    // redraw the screen
        requestLayout() // when rectangular progress dimension changes
    }

    // call after downloading is completed
    fun hasCompletedDownload() {
        // cancel the animation when file is downloaded
        valueAnimator.cancel()
        invalidate()
        requestLayout()
    }

    // initialize
    init {
        isClickable = true
        valueAnimator = AnimatorInflater.loadAnimator(
            context,
            // properties for downloading progress is defined
            R.anim.loading_animation
        ) as ValueAnimator

        valueAnimator.addUpdateListener(updateListener)

        // initialize custom attributes of the button
        val attr = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressLineView,
            0,
            0
        )
        try {

            // button back-ground color
            bgColor = attr.getColor(
                R.styleable.ProgressLineView_bgColor,
                ContextCompat.getColor(context, R.color.background_line_01)
            )

            // button text color
            textColor = attr.getColor(
                R.styleable.ProgressLineView_textColor,
                ContextCompat.getColor(context, R.color.text_color)
            )
        } finally {
            // clearing all the data associated with attribute
            attr.recycle()
        }
    }

    // set attributes of paint
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER // button text alignment
        textSize = 36.0f //button text size
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    // start the animation when button is clicked
    fun animation() {
        valueAnimator.start()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set the dimensions and radius of the rounded rectangle
        val x = 0f
        val y = 0f
        val radius = 32f

        paint.strokeWidth = 0f
        paint.color = bgColor
        // draw custom button

        canvas.drawRoundRect(RectF(x, y, x + width.toFloat(), y + height.toFloat()), radius, radius, paint)

        // to show rectangular progress on custom button while file is downloading
        paint.color = Color.parseColor("#40C9C4")

        canvas.drawRoundRect(RectF(x, y, (width * (progress / 100)).toFloat(), y + height.toFloat()), radius, radius, paint)

        // check the button state
        val buttonText = progress.toInt().toString() + " %" // Loading percentage values

        // write the text on custom button
        paint.color = Color.parseColor("#4873A6")
        canvas.drawText(buttonText, (width / 2).toFloat(), ((height + 30) / 2).toFloat(), paint)
    }

}
