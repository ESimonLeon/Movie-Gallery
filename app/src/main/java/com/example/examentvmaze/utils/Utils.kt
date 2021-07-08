package com.example.examentvmaze

import android.content.Context
import android.content.res.Configuration
import com.example.examentvmaze.constant.AppConstant.DATE_FORMAT
import com.example.examentvmaze.constant.AppConstant.DOMINGO
import com.example.examentvmaze.constant.AppConstant.JUEVES
import com.example.examentvmaze.constant.AppConstant.LENGUAJE_TAG
import com.example.examentvmaze.constant.AppConstant.LUNES
import com.example.examentvmaze.constant.AppConstant.MARTES
import com.example.examentvmaze.constant.AppConstant.MIERCOLES
import com.example.examentvmaze.constant.AppConstant.SABADO
import com.example.examentvmaze.constant.AppConstant.VIERNES
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val dfs = DateFormatSymbols()
val months = dfs.months
val calendar = Calendar.getInstance()

fun getTitleToolbar(): String {
    return nameDay(calendar).plus(" ")
        .plus(calendar.get(Calendar.DAY_OF_MONTH)).plus(" de ")
        .plus(months[calendar.get(Calendar.MONTH)]).plus(" ")
        .plus(calendar.get(Calendar.YEAR))
}

fun nameDay(calendar: Calendar): String {
    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> LUNES
        Calendar.TUESDAY -> MARTES
        Calendar.WEDNESDAY -> MIERCOLES
        Calendar.THURSDAY -> JUEVES
        Calendar.FRIDAY -> VIERNES
        Calendar.SATURDAY -> SABADO
        else -> DOMINGO
    }
}

fun getDateNow(): String? {
    val calendar = Calendar.getInstance()
    calendar.set(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    return SimpleDateFormat(DATE_FORMAT, Locale.forLanguageTag(LENGUAJE_TAG)).format(calendar.time)
}


fun arrayStringConvertText(array: ArrayList<String>): String? = with(array) {
    if (size > 0) toString().replace("[", " ").replace("]", "")
    else ""
}

fun recyclerViewSpanCount(context: Context): Int =
    when (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
        Configuration.SCREENLAYOUT_SIZE_LARGE -> if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        Configuration.SCREENLAYOUT_SIZE_SMALL, Configuration.SCREENLAYOUT_SIZE_NORMAL -> 1
        else -> if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
    }
