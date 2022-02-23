package com.oguzhanorhan.rawggamedatabaseandroid.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(
    currentFormat: String = Configs.Format.serviceDateFormat,
    newFormat: String = Configs.Format.appDateFormat
): String {

    return try {
        val simpleDateFormat = SimpleDateFormat(currentFormat, Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val currentDate: Date = simpleDateFormat.parse(this) ?: Date()
        val newDateFormat = SimpleDateFormat(newFormat, Locale.ENGLISH)
        newDateFormat.timeZone = TimeZone.getDefault()
        newDateFormat.format(currentDate)
    } catch (e: Exception) {
        this
    }
}
