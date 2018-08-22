package com.example.danny.gitstar

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ListView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

object Matchers {

    fun withListSize(size: Int): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                return (view as RecyclerView).adapter?.itemCount == size
            }

            override fun describeTo(description: Description) {
                description.appendText("ListView should have $size items")
            }
        }
    }
}