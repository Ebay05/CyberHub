package com.example.mycyberhub.data.repository

import com.example.mycyberhub.network.RetrofitClient

object CveRepository {

    suspend fun getMonthlyCveCount(): Int {

        val (startDate, endDate) =
            MonthlyStats.getCurrentMonthRange()

        val response = RetrofitClient.api.getMonthlyCveCount(
            startDate = startDate,
            endDate = endDate
        )

        return response.totalResults
    }
}