package com.example.danny.gitstar.util

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent

object LinkUtils {

    fun launchLink(context : Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}