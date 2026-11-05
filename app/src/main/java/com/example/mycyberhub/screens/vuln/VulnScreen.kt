    package com.example.mycyberhub.screens.vuln

    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import com.example.mycyberhub.components.MainTopBar
    import com.example.mycyberhub.components.VulnList

    @Composable
    fun VulnScreen(
        onCveClick: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainTopBar(
                type = "MainView",
                title = "Podatności"
            )

            VulnList(
                onCveClick = onCveClick
            )
        }
    }