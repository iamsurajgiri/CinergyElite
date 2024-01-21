package tuna.cinergyelite.utils

import tuna.cinergyelite.booking.DayDate
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun convertDateFormat(inputDate: String): DayDate {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("EEE dd MMM", Locale.ENGLISH)

        val date = inputFormat.parse(inputDate)
        val formattedDate = date?.let { outputFormat.format(it) }

        val parts = formattedDate?.split(" ")

        return DayDate(parts?.get(0) ?: "", parts?.get(1) ?: "", parts?.get(2) ?: "")
    }
}