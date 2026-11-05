package com.example.mycyberhub.data.repository

import java.time.YearMonth
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object MonthlyStats {

    fun getCurrentMonthRange(): Pair<String, String> {
        val month = YearMonth.now(ZoneOffset.UTC)

        val formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd'T'HH:mm:ss.SSS"
        )

        val startDate = month
            .atDay(1)
            .atStartOfDay()
            .format(formatter)

        val endDate = month
            .atEndOfMonth()
            .atTime(23, 59, 59, 999_000_000)
            .format(formatter)

        return Pair(startDate, endDate)
    }
}