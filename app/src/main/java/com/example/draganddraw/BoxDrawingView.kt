package com.example.draganddraw

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "BoxDrawingView"

class BoxDrawingView (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    event?.let {
      val currentPoint = PointF(it.x, it.y)
      var action = ""
      when (it.action) {
        MotionEvent.ACTION_DOWN -> {
          action = "ACTION_DOWN"
        }
        MotionEvent.ACTION_MOVE -> {
          action = "ACTION_MOVE"
        }
        MotionEvent.ACTION_UP -> {
          action = "ACTION_UP"
        }
        MotionEvent.ACTION_CANCEL -> {
          action = "ACTION_CANCEL"
        }
      }

      Log.i (TAG, "$action at x = ${currentPoint.x}, y = ${currentPoint.y}")

      return true
    }

    return false
  }
}