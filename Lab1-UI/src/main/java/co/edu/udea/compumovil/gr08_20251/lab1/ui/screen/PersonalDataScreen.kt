package co.edu.udea.compumovil.gr08_20251.lab1.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PersonalDataScreen(navigateToContactData: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Form(navigateToContactData)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(navigateToContactData: () -> Unit) {
    TopAppBar(
        title = { Text("Personal Data") }
    )
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToContactData() }) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}