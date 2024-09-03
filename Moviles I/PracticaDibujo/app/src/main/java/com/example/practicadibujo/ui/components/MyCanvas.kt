package com.example.practicadibujo.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val objPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        objPaint.color = Color.RED
        objPaint.strokeWidth = 10f
        canvas?.drawLine(0f, 0f, 300f, 300f, objPaint)
        objPaint.color = Color.BLUE
        canvas?.drawRect(200f, 200f, 500f, 500f, objPaint)
        objPaint.color = Color.GREEN
        canvas?.drawCircle(500f, 500f, 100f, objPaint)
    }

}