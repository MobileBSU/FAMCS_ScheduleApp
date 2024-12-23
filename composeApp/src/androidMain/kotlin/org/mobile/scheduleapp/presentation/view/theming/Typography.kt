package org.mobile.scheduleapp.presentation.view.theming

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.mobile.scheduleapp.R


@OptIn(ExperimentalTextApi::class)
val Inter = FontFamily(
    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.ExtraBold,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(800),
            FontVariation.width(100f)
        )
    ),
    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.Bold,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(700)
        )
    ),

    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    ),
    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    ),

    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    ),

    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.Light,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(300)
        )
    ),

    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.ExtraLight,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(200)
        )
    ),

    Font(
        resId = R.font.variable_inter_font,
        weight = FontWeight.Thin,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(100)
        )
    )

)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    bodyLarge= TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )
)