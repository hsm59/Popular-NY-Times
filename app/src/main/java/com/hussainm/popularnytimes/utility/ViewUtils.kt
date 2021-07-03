package com.hussainm.popularnytimes.utility

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.toggleVisibility(condition: Boolean, defaultVisibility: Int = View.GONE) {
    if (condition) this.visibility = View.VISIBLE else this.visibility = defaultVisibility
}

/**
 * Since [androidx.recyclerview.widget.ListAdapter]'s submit list works in the Background thread, when
 * updating an existing list with new data that might have IDs from the previous list, the recyclerview
 * would scroll the user to the last scrolled location on that list, which
 * might be the ID of the item on the top that later moves to a different
 * location, for these situations, we add a [RecyclerView.AdapterDataObserver] to scroll the user to the
 * top, the function below dispatches event that is called whenever there's a
 * change in range / items are moved / etc
 */
fun scrollToTopAdapterObserver(function: () -> Unit) = object: RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        function()
    }
    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        function()
    }
    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        function()
    }
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        function()
    }
    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        function()
    }
    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        function()
    }
}