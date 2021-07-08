package com.example.examentvmaze.view.movie_detail.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMarginDecoration(val dimension: Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.left = dimension.toInt()
        outRect.top = dimension.toInt()
        outRect.bottom = dimension.toInt()
        outRect.right = dimension.toInt()
    }
}
