package com.example.danny.gitstar.util

import android.support.annotation.ColorRes
import com.example.danny.gitstar.R

object ColorUtils {

    @ColorRes
    fun getLangColor(language: String?): Int {
        when {
            language.equals("java", true) -> return R.color.javaBrown
            language.equals("javascript", true) -> return R.color.javaScriptYellow
            language.equals("ruby", true) -> return R.color.rubyRed
            language.equals("python", true) -> return R.color.pythonBlue
            language.equals("c", true) -> return R.color.cGray
            language.equals("c++", true) -> return R.color.cplusplusPink
            language.equals("go", true) -> return R.color.goBlue
            language.equals("html", true) -> return R.color.htmlPink
            language.equals("scala", true) -> return R.color.scalaPink
            language.equals("shell", true) -> return R.color.shellGreen
            language.equals("css", true) -> return R.color.cssBrown
            language.equals("php", true) -> return R.color.phpGray
            language.equals("objective-c", true) -> return R.color.objectiveCBlue
            language.equals("kotlin", true) -> return R.color.kotlinOrange
            else -> return R.color.langColorDefault
        }
    }
}