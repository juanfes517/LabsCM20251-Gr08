package co.edu.udea.compumovil.gr08_20251.lab1.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(title: String) {
    Column {
        TopAppBar(
            title = { Text(title) },
            modifier = Modifier.height(60.dp)
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 0.dp)
        )
    }
}

// example of icon id: R.drawable.person
@Composable
fun CustomIcon(modifier: Modifier = Modifier, iconId: Int, iconSize: Dp) {
    Image(
        painter = painterResource(iconId),
        contentDescription = null,
        modifier = modifier.size(iconSize),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun CustomNextButton(
    onClickButton: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 30.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onClickButton,
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor  = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth(0.4f)
        ) {
            Text(
                text = "Siguiente",
                fontFamily = balinookBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}