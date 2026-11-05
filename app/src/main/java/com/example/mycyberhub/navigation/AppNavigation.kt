package com.example.mycyberhub.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycyberhub.components.BottomNav
import com.example.mycyberhub.components.MainTopBar
import com.example.mycyberhub.screens.CVEListScreen
import com.example.mycyberhub.screens.dashboard.DashboardScreen
import com.example.mycyberhub.screens.intro.IntroScreen
import com.example.mycyberhub.screens.news.NewsScreen
import com.example.mycyberhub.screens.vuln.VulnScreen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val currentDate = LocalDateTime.now().format(formatter)

    val tabs = listOf("dashboard", "vulns", "news", "intro")



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),

        topBar = {
            MainTopBar(
                when (currentRoute) {
                    "dashboard" -> "Home"
                    "vulns" -> "Vulnerabilities"
                    "cve" -> "CVE Database"
                    "news" -> "News"
                    "intro" -> "Introduction"
                    else -> "MyCyberHub"
                }
            )
        },

        bottomBar = {
            BottomNav(
                selectedTab = when (currentRoute) {
                    "dashboard" -> 0
                    "vulns", "cve" -> 1
                    "news" -> 2
                    "intro" -> 3
                    else -> 0
                },                onTabSelected = { index ->
                    val route = tabs[index]

                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("dashboard") {
                DashboardScreen(
                    todayDate = currentDate
                )
            }

            composable("vulns") {
                VulnScreen(
                    onCveClick = {
                        navController.navigate("cve")
                    }
                )
            }

            composable("cve") {
                CVEListScreen(
                    navController = navController
                )
            }

            composable("news") {
                NewsScreen()
            }

            composable("intro") {
                IntroScreen()
            }
        }
    }
}