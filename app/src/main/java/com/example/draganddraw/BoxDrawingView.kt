package com.example.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "BoxDrawingView"

class BoxDrawingView (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

  private var currentBox: Box? = null
  private val boxes = mutableListOf<Box>()
  private val boxPaint = Paint().apply {
    color = 0x22ff0000.toInt()
  }
  private val backgroundPaint = Paint().apply {
    color = 0xfff8efe0.toInt()
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    event?.let { event ->
      val currentPoint = PointF(event.x, event.y)
      var action = ""
      when (event.action) {
        MotionEvent.ACTION_DOWN -> {
          action = "ACTION_DOWN"
          currentBox = Box(currentPoint).also {
            boxes.add(it)
          }
        }
        MotionEvent.ACTION_MOVE -> {
          action = "ACTION_MOVE"
          updateCurrentBox(currentPoint)
          currentBox = null
        }
        MotionEvent.ACTION_UP -> {
          action = "ACTION_UP"
        }
        MotionEvent.ACTION_CANCEL -> {
          action = "ACTION_CANCEL"
          currentBox = null
        }
      }

      Log.i (TAG, "$action at x = ${currentPoint.x}, y = ${currentPoint.y}")

      return true
    }

    return false
  }

  override fun onDraw(canvas: Canvas?) {
    canvas?.let {  canvas ->
      canvas.drawPaint(backgroundPaint)

      boxes.forEach {
        canvas.drawRect(it.left, it.top, it.right, it.bottom, boxPaint)
      }
    }
  }

  private fun updateCurrentBox(currentPoint: PointF) {
    currentBox?.let {
      it.end = currentPoint
      // invalidate() заставляет BoxDrawingView перерисовать себя,
      // чтобы пользователь видел прямоугольник в процессе перетаскивания
      invalidate()
    }
  }
}