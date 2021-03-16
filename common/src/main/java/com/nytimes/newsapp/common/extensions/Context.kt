package com.nytimes.newsapp.common.extensions
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.ColorInt


@ColorInt
@SuppressLint("Recycle")
//fun Context.themeColor(
//    @AttrRes themeAttrId: Int
//): Int {
//    return obtainStyledAttributes(
//        intArrayOf(themeAttrId)
//    ).use {
//        it.getColor(0, Color.MAGENTA)
//    }
//}

fun Context.currentLocale():String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0].language
    } else {
        resources.configuration.locale.language
    }?:"en"

}

