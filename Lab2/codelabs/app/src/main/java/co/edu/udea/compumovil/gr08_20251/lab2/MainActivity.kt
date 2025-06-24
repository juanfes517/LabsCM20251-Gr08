package co.edu.udea.compumovil.gr08_20251.lab2

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr08_20251.lab2.corrutinas.ui.RaceTrackerApp
import co.edu.udea.compumovil.gr08_20251.lab2.retrofit.MarsPhotosApp
import co.edu.udea.compumovil.gr08_20251.lab2.ui.theme.Lab2CorrutinasTheme
import co.edu.udea.compumovil.gr08_20251.lab2.worker.ui.BluromaticScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2CorrutinasTheme {
                NavigationScreen()
            }
        }
    }
}

@Composable
fun NavigationScreen() {
    var currentScreen by remember { mutableStateOf("navigation") }
    
    when (currentScreen) {
        "navigation" -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Laboratorio 2",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                
                Button(
                    onClick = { currentScreen = "bluromatic" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Bluromatic - WorkManager")
                }
                
                Button(
                    onClick = { currentScreen = "mars" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Mars Photos - Retrofit")
                }
                
                Button(
                    onClick = { currentScreen = "race" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Race Tracker - Corrutinas")
                }
            }
        }
        "bluromatic" -> {
            Column {
                Button(
                    onClick = { currentScreen = "navigation" },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("← Volver al Menú")
                }
                BluromaticScreen()
            }
        }
        "mars" -> {
            Column {
                Button(
                    onClick = { currentScreen = "navigation" },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("← Volver al Menú")
                }
                MarsPhotosApp()
            }
        }
        "race" -> {
            Column {
                Button(
                    onClick = { currentScreen = "navigation" },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("← Volver al Menú")
                }
                RaceTrackerApp()
            }
        }
    }
}

fun Context.getImageUri(): Uri {
    val resources = this.resources

    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
        .build()
}