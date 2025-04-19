package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header

@Composable
fun PersonalDataScreen(navigateToContactData: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Form(navigateToContactData)
    }
}

@Composable
fun Form(navigateToContactData: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header("Información personal")
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToContactData() }) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}