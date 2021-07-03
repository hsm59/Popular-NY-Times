package com.hussainm.popularnytimes.utility

import android.view.View

fun View.toggleVisibility(condition: Boolean, defaultVisibility: Int = View.GONE) {
    if (condition) this.visibility = View.VISIBLE else this.visibility = View.GONE
}