package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardCapitalization
import co.edu.udea.compumovil.gr08_20251.lab1.R
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomNextButton
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomTextField
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header
import co.edu.udea.compumovil.gr08_20251.lab1.ui.viewModel.ContactDataViewModel

@Composable
fun ContactDataScreen(
    navigateToReset: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            Header("Información de contacto")
            FormContactData(navigateToReset)
        }
    }
}

@Composable
fun FormContactData(
    navigateToReset: () -> Unit,
    viewModel: ContactDataViewModel = viewModel()
){

    val uiState by viewModel.uiState.collectAsState()

    // Lista de países de Latinoamérica
    val countries = listOf(
        "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica",
        "Cuba", "Ecuador", "El Salvador", "Guatemala", "Haití", "Honduras",
        "México", "Nicaragua", "Panamá", "Paraguay", "Perú",
        "República Dominicana", "Uruguay", "Venezuela"
    )

    // Lista de ciudades principales de Colombia
    val cities = listOf(
        "Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta",
        "Bucaramanga", "Pereira", "Santa Marta", "Ibagué"
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        // Teléfono
        CustomTextField(
            userInput = viewModel.userInputPhone,
            onUserInputChanged = { viewModel.updateUserInputPhone(it) },
            iconId = R.drawable.call,
            labelText = "Teléfono",
            keyboardCapitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            isInputNull = uiState.isInputPhoneNull,
            maxComponentWidth = 0.9f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        CustomTextField(
            userInput = viewModel.userInputEmail,
            onUserInputChanged = { viewModel.updateUserInputEmail(it) },
            iconId = R.drawable.mail,
            labelText = "Correo",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = uiState.isInputEmailNull,
            maxComponentWidth = 0.9f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // País (Autocomplete)
        CustomTextField(
            userInput = viewModel.userCountry,
            onUserInputChanged = { viewModel.updateUserCountry(it) },
            iconId = R.drawable.globe_location_pin,
            labelText = "País",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = uiState.isInputCountryNull,
            maxComponentWidth = 0.9f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Ciudad (Autocomplete)
        CustomTextField(
            userInput = viewModel.userCity,
            onUserInputChanged = { viewModel.updateUserCity(it) },
            iconId = R.drawable.location_city,
            labelText = "Ciudad",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = false,
            maxComponentWidth = 0.9f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Dirección
        CustomTextField(
            userInput = viewModel.userAddress,
            onUserInputChanged = { viewModel.updateUserAddress(it) },
            iconId = R.drawable.home_pin,
            labelText = "Dirección",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = false,
            maxComponentWidth = 0.9f
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomNextButton(
            onClickButton = { viewModel.checkUserInputs(navigateToReset) },
        )
    }
}