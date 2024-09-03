package com.example.practicadibujoontouch.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.sqrt

class MyCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val objPaint: Paint = Paint()
    private var xStart = 0f
    private var yStart = 0f
    private var xEnd = 0f
    private var yEnd = 0f
    var figure: Shape = Shape.LINE
    var bitmap: Bitmap? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        objPaint.strokeWidth = 10f
        objPaint.color = Color.RED

        bitmap?.let {
            canvas?.drawBitmap(it, 0f, 0f, objPaint)
        }

        when (figure) {
            Shape.LINE -> {
                canvas?.drawLine(xStart, yStart, xEnd, yEnd, objPaint)
            }
            Shape.CIRCLE -> {
                var radius =
                    sqrt((xEnd - xStart) * (xEnd - xStart) + (yEnd - yStart) * (yEnd - yStart))
                canvas?.drawCircle(xStart, yStart, radius, objPaint)
            }
            Shape.RECTANGLE -> {
                canvas?.drawRect(xStart, yStart, xEnd, yEnd, objPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                xStart = event.x
                yStart = event.y
            }

            MotionEvent.ACTION_UP -> {
                bitmap = getBitmapFromView(this)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                xEnd = event.x
                yEnd = event.y
                invalidate()
            }
        }
        return true
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }


}