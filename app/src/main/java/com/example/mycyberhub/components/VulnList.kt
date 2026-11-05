package com.example.mycyberhub.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class VulnType(
    val name: String,
    val status: String,
)

@Composable
fun VulnList(
    onCveClick: () -> Unit
) {

    val vulnTypeList = listOf(
        VulnType("CVE", "Implemented"),
//        VulnType("CWE", "Implemented"),
//        VulnType("CPE", "Implemented"),
//        VulnType("CVSS", "Implemented"),
//        VulnType("KEV", "None"),
//        VulnType("EPSS", "None"),
//        VulnType("CAPEC", "None"),
//        VulnType("ATT&CK", "None"),
//        VulnType("NVD", "Implemented"),
//        VulnType("OSV", "None"),
//        VulnType("GHSA", "None"),
//        VulnType("CSAF", "None"),
//        VulnType("VEX", "None"),
//        VulnType("SSVC", "None"),
//        VulnType("CERT/CC", "None"),
//        VulnType("Bugtraq", "None"),
//        VulnType("Exploit-DB", "None"),
//        VulnType("OWASP Top 10", "None"),
//        VulnType("OWASP ASVS", "None"),
//        VulnType("OWASP WSTG", "None"),
//        VulnType("SANS CWE Top 25", "None"),
//        VulnType("Zero-Day", "None"),
//        VulnType("Vendor Advisories", "None")
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(vulnTypeList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                onClick = {
                    if (item.name == "CVE") {
                        onCveClick()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                }
            }
        }
    }
}