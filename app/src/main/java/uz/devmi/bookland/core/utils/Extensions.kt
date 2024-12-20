package uz.devmi.bookland.core.utils

import android.net.Uri

fun String.extractPageNumber(): Int? {
    return try {
        Uri.parse(this).getQueryParameter("page")?.toInt()
    } catch (e: Exception) {
        null
    }
}
