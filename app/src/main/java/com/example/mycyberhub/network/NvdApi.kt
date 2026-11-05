package com.example.mycyberhub.network

import com.example.mycyberhub.data.CveResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NvdApi {

    @GET("cves/2.0")
    suspend fun getCves(
        @Query("resultsPerPage") resultsPerPage: Int = 50,
        @Query("startIndex") startIndex: Int = 0,
        @Query("keywordSearch") keyword: String? = null,
        @Query("cveId") cveId: String? = null,
        @Query("cweId") cweId: String? = null,
        @Query("cvssV3Severity") severity: String? = null
    ): CveResponse

    @GET("cves/2.0")
    suspend fun getMonthlyCveCount(
        @Query("pubStartDate") startDate: String,
        @Query("pubEndDate") endDate: String,
        @Query("resultsPerPage") resultsPerPage: Int = 1
    ): CveResponse
}