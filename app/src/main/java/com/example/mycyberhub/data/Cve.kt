
package com.example.mycyberhub.data

data class CveResponse(
    val resultsPerPage: Int,
    val startIndex: Int,
    val totalResults: Int,
    val vulnerabilities: List<CveItem> = emptyList()
)

data class CveItem(
    val cve: Cve
)

data class Cve(
    val id: String,
    val published: String,
    val descriptions: List<CveDescription> = emptyList(),
    val metrics: CveMetrics? = null,
    val weaknesses: List<CveWeakness> = emptyList()
)

data class CveDescription(
    val lang: String,
    val value: String
)

// CVSS
data class CveMetrics(
    val cvssMetricV31: List<CvssMetric>? = null,
    val cvssMetricV30: List<CvssMetric>? = null,
    val cvssMetricV2: List<CvssMetric>? = null
)

data class CvssMetric(
    val cvssData: CvssData
)

data class CvssData(
    val baseScore: Double,
    val baseSeverity: String? = null
)

// CWE
data class CveWeakness(
    val description: List<CveWeaknessDescription> = emptyList()
)

data class CveWeaknessDescription(
    val lang: String,
    val value: String
)
