package com.example.danny.gitstar.util

import android.app.Activity
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView


object ViewUtils {

    fun hideSoftKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }

    fun setTextViewDrawableColor(textView: TextView, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.compoundDrawableTintList = ContextCompat.getColorStateList(textView.context, color)
        }
    }

    fun booleanToVisibility(isTrue: Boolean): Int {
        return if (isTrue) View.VISIBLE else View.GONE
    }
}

