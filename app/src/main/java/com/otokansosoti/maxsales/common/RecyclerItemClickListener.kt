package com.otokansosoti.maxsales.common

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(private val context: Context, private final val recyclerView: RecyclerView, private val listener: AdapterView.OnItemClickListener): RecyclerView.OnItemTouchListener {

    val gestureDetector: GestureDetector = GestureDetector(context, GestureDetector.SimpleOnGestureListener())

    interface OnItemCLickListener: AdapterView.OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}