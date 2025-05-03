package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.R

@Composable
fun ResetScreen(navigateToPersonalData: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.thanks), fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToPersonalData() }) {
            Text(text = stringResource(R.string.reset))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}