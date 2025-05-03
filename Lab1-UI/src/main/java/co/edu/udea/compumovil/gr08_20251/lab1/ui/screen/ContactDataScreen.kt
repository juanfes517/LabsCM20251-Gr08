package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.R
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomIcon
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomNextButton
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomTextField
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookBold
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookRegular
import co.edu.udea.compumovil.gr08_20251.lab1.ui.viewModel.ContactDataViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalConfiguration


@Composable
fun ContactDataScreen(
    navigateToReset: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            Header("Información de contacto")

            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    FormContactDataPortrait(navigateToReset)
                }

                Configuration.ORIENTATION_LANDSCAPE -> {
                    FormContactDataLandscape(navigateToReset)
                }
            }
        }
    }
}

@Composable
fun FormContactDataPortrait(
    navigateToReset: () -> Unit,
    viewModel: ContactDataViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val countryNames by viewModel.countryNames.collectAsState()
    val cityNames by viewModel.cityNames.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
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
            modifier = Modifier.fillMaxWidth(0.9f),
        )

        // Email
        CustomTextField(
            userInput = viewModel.userInputEmail,
            onUserInputChanged = { viewModel.updateUserInputEmail(it) },
            iconId = R.drawable.mail,
            labelText = "Correo",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = uiState.isInputEmailNull,
            modifier = Modifier.fillMaxWidth(0.9f),
        )

        // País (Autocomplete)
        AutoCompleteCustomTextField(
            userInput = viewModel.userCountry,
            onUserInputChanged = { viewModel.updateUserCountry(it) },
            iconId = R.drawable.globe_location_pin,
            labelText = "País",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = uiState.isInputCountryNull,
            modifier = Modifier.fillMaxWidth(0.9f),
            list = countryNames
        )

        // Ciudad (Autocomplete)
        AutoCompleteCustomTextField(
            userInput = viewModel.userCity,
            onUserInputChanged = { viewModel.updateUserCity(it) },
            iconId = R.drawable.location_city,
            labelText = "Ciudad",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = false,
            modifier = Modifier.fillMaxWidth(0.9f),
            list = cityNames
        )

        // Dirección
        CustomTextField(
            userInput = viewModel.userAddress,
            onUserInputChanged = { viewModel.updateUserAddress(it) },
            iconId = R.drawable.home_pin,
            labelText = "Dirección",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = false,
            modifier = Modifier.fillMaxWidth(0.9f),
        )

        CustomNextButton(
            onClickButton = { viewModel.checkUserInputs(navigateToReset) },
        )
    }
}

@Composable
fun FormContactDataLandscape(
    navigateToReset: () -> Unit,
    viewModel: ContactDataViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val countryNames by viewModel.countryNames.collectAsState()
    val cityNames by viewModel.cityNames.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 10.dp)
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
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
            )

            // Email
            CustomTextField(
                userInput = viewModel.userInputEmail,
                onUserInputChanged = { viewModel.updateUserInputEmail(it) },
                iconId = R.drawable.mail,
                labelText = "Correo",
                keyboardCapitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                isInputNull = uiState.isInputEmailNull,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 10.dp)
        ) {
            // País (Autocomplete)
            AutoCompleteCustomTextField(
                userInput = viewModel.userCountry,
                onUserInputChanged = { viewModel.updateUserCountry(it) },
                iconId = R.drawable.globe_location_pin,
                labelText = "País",
                keyboardCapitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                isInputNull = uiState.isInputCountryNull,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                list = countryNames
            )

            // Ciudad (Autocomplete)
            AutoCompleteCustomTextField(
                userInput = viewModel.userCity,
                onUserInputChanged = { viewModel.updateUserCity(it) },
                iconId = R.drawable.location_city,
                labelText = "Ciudad",
                keyboardCapitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                isInputNull = false,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                list = cityNames
            )
        }

        // Dirección
        CustomTextField(
            userInput = viewModel.userAddress,
            onUserInputChanged = { viewModel.updateUserAddress(it) },
            iconId = R.drawable.home_pin,
            labelText = "Dirección",
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = false,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 18.dp),
        )

        CustomNextButton(
            onClickButton = { viewModel.checkUserInputs(navigateToReset) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteCustomTextField(
    userInput: String,
    onUserInputChanged: (String) -> Unit,
    iconId: Int,
    labelText: String,
    keyboardCapitalization: KeyboardCapitalization,
    keyboardType: KeyboardType,
    isInputNull: Boolean,
    modifier: Modifier = Modifier,
    list: List<String>
) {

    var expanded by remember { mutableStateOf(false) }

    val filteredCities = list.filter {
        it.startsWith(userInput, ignoreCase = true)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 10.dp, bottom = 5.dp)
    ) {
        CustomIcon(
            modifier = Modifier.offset(y = (5).dp),
            iconId = iconId,
            iconSize = 40.dp
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded && filteredCities.isNotEmpty(),
                onExpandedChange = { expanded = it }
            ) {

                TextField(
                    value = userInput,
                    onValueChange = {
                        onUserInputChanged(it)
                        expanded = true
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(1f),
                            text = labelText,
                            style = TextStyle(
                                fontFamily = balinookBold,
                                fontSize = 14.sp,
                                color =
                                    if (!isInputNull) MaterialTheme.colorScheme.secondary
                                    else MaterialTheme.colorScheme.error,
                            )
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = balinookRegular
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = keyboardCapitalization,
                        autoCorrectEnabled = false,
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Next,
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.heightIn(max = 150.dp)
                ) {
                    filteredCities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city) },
                            onClick = {
                                onUserInputChanged(city)
                                expanded = false
                            }
                        )
                    }
                }
            }

            HorizontalDivider(
                thickness = 2.dp,
                color =
                    if (!isInputNull) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth(0.9f)
            )
        }
    }
}