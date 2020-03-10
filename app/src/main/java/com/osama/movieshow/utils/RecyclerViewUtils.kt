package com.osama.movieshow.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewUtils {

    private val TAG = RecyclerViewUtils::class.java.simpleName

    fun setupLinearHorizontalRecView(recyclerView: RecyclerView, context: Context?) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
    }

    fun setupLinearVerticalRecView(
        recyclerView: RecyclerView,
        context: Context?
    ) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
    }

    fun setupStaggeredGridRecView(
        recyclerView: RecyclerView,
        context: Context?
    ) {
        recyclerView.setHasFixedSize(true)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
    }

    fun setupGridRecView(
        recyclerView: RecyclerView,
        context: Context?,
        spanCount: Int
    ) {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, spanCount)
        recyclerView.layoutManager = gridLayoutManager
    }

    fun setupChatRecyclerView(
        context: Context?,
        recyclerView: RecyclerView
    ) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager
    }


}