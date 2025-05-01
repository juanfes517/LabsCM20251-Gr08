package co.edu.udea.compumovil.gr08_20251.lab1.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.gr08_20251.lab1.ui.uiState.PersonalDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

class PersonalDataViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PersonalDataUiState())
    val uiState: StateFlow<PersonalDataUiState> = _uiState.asStateFlow()

    var userInputName by mutableStateOf("")
        private set

    var userInputLastName by mutableStateOf("")
        private set

    var userGender by mutableStateOf("")
        private set

    var userBirthday by mutableStateOf<Long?>(null)
        private set

    var userEducationLevel by mutableStateOf("")
        private set

    fun updateUserInputName(nameEntered: String) {
        userInputName = nameEntered
    }

    fun updateUserInputLastName(lastNameEntered: String) {
        userInputLastName = lastNameEntered
    }

    fun updateUserGender(userGenderEntered: String) {
        userGender = userGenderEntered
    }

    fun updateUserBirthday(userBirthdayEntered: Long?) {
        userBirthday = userBirthdayEntered
    }

    fun updateUserEducationLevel(userEducationLevelEntered: String) {
        userEducationLevel = userEducationLevelEntered
    }

    fun checkUserInputs(navigateToContactData: () -> Unit) {

        userInputName = userInputName.trim()
        userInputName = userInputName.replace(Regex("\\s+"), " ")

        userInputLastName = userInputLastName.trim()
        userInputLastName = userInputLastName.replace(Regex("\\s+"), " ")

        if (userInputName.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputNameNull = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputNameNull = false
                )
            }
        }

        if (userInputLastName.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputLastNameNull = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputLastNameNull = false
                )
            }
        }

        if (userBirthday == null) {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputBirthdayNull = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isInputBirthdayNull = false
                )
            }
        }

        if (!userInputName.isEmpty() && !userInputLastName.isEmpty() && userBirthday != null) {
            printAllFields()
            navigateToContactData()
        }
    }

    private fun printAllFields() {
        Log.i("PersonalData", "------------------------------------------------------")

        Log.i("PersonalData", "Información Personal: ")
        Log.i("PersonalData", "$userInputName $userInputLastName")

        if (userGender != "") {
            if (userGender == "Hombre") {
                Log.i("PersonalData", "Masculino")
            } else {
                Log.i("PersonalData", "Femenino")
            }
        }

        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val formattedDate = formatter.format(userBirthday)
        Log.i("PersonalData", "Nació el $formattedDate")

        if (userEducationLevel != "") {
            Log.i("PersonalData", userEducationLevel)
        }

        Log.i("PersonalData", "------------------------------------------------------")
    }
}