package co.edu.udea.compumovil.gr08_20251.lab1.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
fun CustomIcon(iconId: Int, iconSize: Dp) {
    Image(
        painter = painterResource(iconId),
        contentDescription = null,
        modifier = Modifier.size(iconSize),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}