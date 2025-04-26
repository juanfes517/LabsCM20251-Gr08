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

class PersonalDataViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PersonalDataUiState())
    val uiState: StateFlow<PersonalDataUiState> = _uiState.asStateFlow()

    var userInputName by mutableStateOf("")
        private set

    var userInputLastName by mutableStateOf("")
        private set

    fun updateUserInputName(nameEntered: String) {
        userInputName = nameEntered
    }

    fun updateUserInputLastName(lastNameEntered: String) {
        userInputLastName = lastNameEntered
    }

    fun checkUserInputs(navigateToContactData: () -> Unit) {
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

        if(userInputLastName.isEmpty()) {
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

        if (!userInputName.isEmpty() && !userInputLastName.isEmpty()) {
            printAllFields()
            navigateToContactData()
        }
    }

    private fun printAllFields() {
        Log.i("PersonalData", "Información Personal: ")
        Log.i("PersonalData", "$userInputName $userInputLastName")
    }
}