package co.edu.udea.compumovil.gr08_20251.lab1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.R

val balinookRegular = FontFamily(
    Font(R.font.balinook_regular)
)

val balinookBold = FontFamily(
    Font(R.font.balinook_bold)
)

val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = balinookRegular,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = balinookBold,
        fontSize = 25.sp
    ),
    labelSmall = TextStyle(
        fontFamily = balinookRegular,
        fontSize = 11.sp
    )
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)