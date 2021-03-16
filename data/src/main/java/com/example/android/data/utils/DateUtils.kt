package com.example.android.data.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun validateDate(strDate: String): String? {
    /* Check if date is 'null' */
    return if (strDate.isBlank()) {
        null
    } else {
        try {
            val inputFormatter =
                    DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.ENGLISH
                    )
            val outputFormatter =
                    DateTimeFormatter.ofPattern("dd MMMM", Locale.ENGLISH)
            val date =
                    LocalDate.parse(strDate, inputFormatter)
            return outputFormatter.format(date)
        } catch (e: Exception) {
            null
        }
    }
}
