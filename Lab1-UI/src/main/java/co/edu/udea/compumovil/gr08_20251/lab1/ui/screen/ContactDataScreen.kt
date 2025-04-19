package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header

@Composable
fun ContactDataScreen(navigateToResetData: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header("Información de contacto")
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToResetData() }) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}