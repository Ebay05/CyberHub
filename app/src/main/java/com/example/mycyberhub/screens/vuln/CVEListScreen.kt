package com.example.mycyberhub.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mycyberhub.components.MainTopBar
import com.example.mycyberhub.viewmodel.CveViewModel

@Composable
fun CVEListScreen(
    navController: NavController,
    cveViewModel: CveViewModel = viewModel()
) {
    val cves by cveViewModel.cves.collectAsStateWithLifecycle()
    val sortedCves = cves.sortedByDescending {
        it.cve.published
    }
    val isLoading by cveViewModel.isLoading.collectAsStateWithLifecycle()
    val error by cveViewModel.error.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        cveViewModel.loadCves()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        MainTopBar(
            type = "Subview",
            title = "CVE",
            onBackClick = {
                navController.popBackStack()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Error: $error")

                        Button(onClick = {
                            cveViewModel.loadCves()
                        }) {
                            Text("Retry")
                        }
                    }
                }
            }

            cves.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No CVE records found")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(sortedCves) { item ->

                        val cve = item.cve

                        val description = cve.descriptions
                            .firstOrNull { it.lang == "en" }
                            ?.value ?: "No description available"

                        val cvss = cve.metrics?.cvssMetricV31?.firstOrNull()
                        val score = cvss?.cvssData?.baseScore
                        val severity = cvss?.cvssData?.baseSeverity ?: "UNKNOWN"

                        val publishedDate = cve.published
                            .take(10)

                        val cwe = cve.weaknesses
                            ?.firstOrNull()
                            ?.description
                            ?.firstOrNull { it.lang == "en" }
                            ?.value ?: "N/A"

                        val severityColor = when (severity.uppercase()) {
                            "CRITICAL" -> Color(0xFFFF5252)
                            "HIGH" -> Color(0xFFFF7043)
                            "MEDIUM" -> Color(0xFFFFB74D)
                            "LOW" -> Color(0xFF66BB6A)
                            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // TODO: przejście do szczegółów CVE
                                },
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = cve.id,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                    Surface(
                                        color = severityColor.copy(alpha = 0.12f),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = severity,
                                            modifier = Modifier.padding(
                                                horizontal = 10.dp,
                                                vertical = 5.dp
                                            ),
                                            color = severityColor,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }

                                Text(
                                    text = description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )

                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.outline
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Column {
                                        Text(
                                            text = "CVSS SCORE",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                        )

                                        Text(
                                            text = score?.toString() ?: "N/A",
                                            style = MaterialTheme.typography.titleLarge,
                                            color = severityColor
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "PUBLISHED",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                        )

                                        Text(
                                            text = publishedDate,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "CWE",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                        )

                                        Text(
                                            text = cwe,
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}