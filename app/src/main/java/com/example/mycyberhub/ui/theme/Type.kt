package com.example.mycyberhub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mycyberhub.R

val SpaceGrotesk = FontFamily(
    Font(
        R.font.space_grotesk_bold,
        FontWeight.Bold
    )
)

val Inter = FontFamily(
    Font(
        R.font.inter_medium,
        FontWeight.Medium
    )
)

private val DefaultTypography = Typography()

val AppTypography = Typography(

    // HEADINGS - SPACE GROTESK BOLD

    displayLarge = DefaultTypography.displayLarge.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = DefaultTypography.displayMedium.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = DefaultTypography.displaySmall.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    headlineLarge = DefaultTypography.headlineLarge.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = DefaultTypography.headlineMedium.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = DefaultTypography.headlineSmall.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = DefaultTypography.titleLarge.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = DefaultTypography.titleMedium.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = DefaultTypography.titleSmall.copy(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold
    ),

    // OTHER TEXT - INTER MEDIUM

    bodyLarge = DefaultTypography.bodyLarge.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = DefaultTypography.bodyMedium.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = DefaultTypography.bodySmall.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    ),
    labelLarge = DefaultTypography.labelLarge.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = DefaultTypography.labelMedium.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = DefaultTypography.labelSmall.copy(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium
    )
)