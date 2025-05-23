package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.udea.compumovil.gr08_20251.lab1.R
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomIcon
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomNextButton
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomTextField
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookBold
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookRegular
import co.edu.udea.compumovil.gr08_20251.lab1.ui.viewModel.PersonalDataViewModel
import java.nio.file.WatchEvent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PersonalDataScreen(
    navigateToContactData: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            Header(stringResource(R.string.personal_information))

            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    FormPortrait(navigateToContactData)
                }

                Configuration.ORIENTATION_LANDSCAPE -> {
                    FormLandscape(navigateToContactData)
                }
            }
        }
    }
}

@Composable
fun FormPortrait(
    navigateToContactData: () -> Unit,
    personalDataViewModel: PersonalDataViewModel = viewModel()
) {
    val personalDataUiState by personalDataViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .verticalScroll(scrollState)
            .imePadding()
    ) {
        CustomTextField(
            userInput = personalDataViewModel.userInputName,
            onUserInputChanged = { personalDataViewModel.updateUserInputName(it) },
            iconId = R.drawable.person,
            labelText = stringResource(R.string.name),
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = personalDataUiState.isInputNameNull,
            modifier = Modifier.fillMaxWidth(0.8f),
            imeAction = ImeAction.Next,
        )

        CustomTextField(
            userInput = personalDataViewModel.userInputLastName,
            onUserInputChanged = { personalDataViewModel.updateUserInputLastName(it) },
            iconId = R.drawable.person_add,
            labelText = stringResource(R.string.lastname),
            keyboardCapitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            isInputNull = personalDataUiState.isInputLastNameNull,
            modifier = Modifier.fillMaxWidth(0.7f),
            imeAction = ImeAction.Next,
        )

        GenderSelection(
            selectedOption = personalDataViewModel.userGender,
            onOptionSelected = { personalDataViewModel.updateUserGender(it) }
        )

        DatePickerFieldToModal(
            selectedDate = personalDataViewModel.userBirthday,
            onDateSelected = { personalDataViewModel.updateUserBirthday(it) },
            isInputBirthdayNull = personalDataUiState.isInputBirthdayNull
        )

        EducationLevelDropdown(
            selectedOption = personalDataViewModel.userEducationLevel,
            onOptionSelected = { personalDataViewModel.updateUserEducationLevel(it) }
        )

        CustomNextButton(
            onClickButton = { personalDataViewModel.checkUserInputs(navigateToContactData) },
        )
    }
}

@Composable
fun FormLandscape(
    navigateToContactData: () -> Unit,
    personalDataViewModel: PersonalDataViewModel = viewModel()
) {
    val personalDataUiState by personalDataViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(15.dp)
            .imePadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 10.dp)
        ) {
            CustomTextField(
                userInput = personalDataViewModel.userInputName,
                onUserInputChanged = { personalDataViewModel.updateUserInputName(it) },
                iconId = R.drawable.person,
                labelText = stringResource(R.string.name),
                keyboardCapitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                isInputNull = personalDataUiState.isInputNameNull,
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Next,
            )

            CustomTextField(
                userInput = personalDataViewModel.userInputLastName,
                onUserInputChanged = { personalDataViewModel.updateUserInputLastName(it) },
                iconId = R.drawable.person_add,
                labelText = stringResource(R.string.lastname),
                keyboardCapitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                isInputNull = personalDataUiState.isInputLastNameNull,
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Next,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GenderSelection(
                selectedOption = personalDataViewModel.userGender,
                onOptionSelected = { personalDataViewModel.updateUserGender(it) }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            DatePickerFieldToModal(
                selectedDate = personalDataViewModel.userBirthday,
                onDateSelected = { personalDataViewModel.updateUserBirthday(it) },
                isInputBirthdayNull = personalDataUiState.isInputBirthdayNull,
                modifier = Modifier.fillMaxWidth(0.2f)
            )
        }

        EducationLevelDropdown(
            selectedOption = personalDataViewModel.userEducationLevel,
            onOptionSelected = { personalDataViewModel.updateUserEducationLevel(it) },
            modifier = Modifier.fillMaxWidth(0.5f)
        )

        Row (
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.End

        ) {
            CustomNextButton(
                onClickButton = { personalDataViewModel.checkUserInputs(navigateToContactData) },
                modifier = Modifier.width(250.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationLevelDropdown(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf(
        stringResource(R.string.elementary),
        stringResource(R.string.secondary),
        stringResource(R.string.college),
        stringResource(R.string.other))

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(start = 10.dp, bottom = 5.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CustomIcon(
            iconId = R.drawable.school,
            iconSize = 40.dp
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(R.string.grade),
                        fontFamily = balinookBold,
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    errorIndicatorColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                    .fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                fontSize = 15.sp
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DatePickerFieldToModal(
    modifier: Modifier = Modifier,
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
    isInputBirthdayNull: Boolean
) {
    var showModal by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(start = 10.dp, bottom = 5.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CustomIcon(
            iconId = R.drawable.calendar_month,
            iconSize = 40.dp
        )

        Text(
            text = stringResource(R.string.birthdate),
            fontFamily = balinookBold,
            fontSize = 15.sp,
            color =
                if (isInputBirthdayNull) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = selectedDate?.let { convertMillisToDate(it) } ?: "",
            onValueChange = { },
            placeholder = {
                Text(
                    text = "MM/DD/YYYY",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            ),
            modifier = modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
                .pointerInput(selectedDate) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showModal = true
                        }
                    }
                },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor =
                    if (isInputBirthdayNull) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor =
                    if (isInputBirthdayNull) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary,
                errorIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
    }

    if (showModal) {
        DatePickerModal(
            onDateSelected = { onDateSelected(it) },
            onDismiss = { showModal = false }
        )
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val scrollState = rememberScrollState()

    DatePickerDialog(
        modifier = Modifier
            .padding(15.dp)
            .verticalScroll(scrollState),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun GenderSelection(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf(stringResource(R.string.man), stringResource(R.string.woman))

    Row(
        modifier = Modifier
            .padding(start = 10.dp, bottom = 5.dp, top = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CustomIcon(
                iconId = R.drawable.group,
                iconSize = 40.dp
            )
            Text(
                text = stringResource(R.string.sex),
                fontFamily = balinookBold,
                fontSize = 17.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            options.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = { onOptionSelected(option) },
                    )
                    Text(
                        text = option,
                        modifier = Modifier.offset(x = (-7).dp)
                    )
                }
            }

        }
    }
}