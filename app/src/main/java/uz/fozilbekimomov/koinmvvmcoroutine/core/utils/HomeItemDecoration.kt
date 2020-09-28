package uz.fozilbekimomov.koinmvvmcoroutine.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 10/25/19}
 */

class HomeItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        outRect.left =spacing
        outRect.right =spacing
        outRect.top =spacing
        outRect.bottom =spacing

        if (position == 0) {
            outRect.left =spacing*2
        }

    }
}