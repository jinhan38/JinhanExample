package com.jinhanexample.customView.compoundView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    private val rect: Rect = Rect()
    private val paint: Paint = Paint()

    init {
        paint.style = Paint.Style.STROKE
        paint.color = -0x7fffff01
    }

    override fun onDraw(canvas: Canvas) {
        val count = lineCount

        // Draws one line in the rectangle for every line of text in the EditText
        for (i in 0 until count) {
            val baseline = getLineBounds(i, rect)
            canvas.drawLine(
                rect.left.toFloat(),
                (baseline + 1).toFloat(),
                rect.right.toFloat(),
                (baseline + 1).toFloat(),
                paint
            )
        }

        super.onDraw(canvas)
    }
}