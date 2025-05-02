package co.edu.udea.compumovil.gr08_20251.lab1.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.gr08_20251.lab1.ui.uiState.ContactDataUiState
import co.edu.udea.compumovil.gr08_20251.lab1.ui.uiState.InputFieldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import android.util.Log
import java.util.regex.Pattern

class ContactDataViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContactDataUiState())
    val uiState: StateFlow<ContactDataUiState> = _uiState.asStateFlow()

    var userInputPhone by mutableStateOf("")
        private set

    var userInputEmail by mutableStateOf("")
        private set

    var userCountry by mutableStateOf("")
        private set

    var userCity by mutableStateOf("")
        private set

    var userAddress by mutableStateOf("")
        private set

    fun updateUserInputPhone(phoneEntered: String) {
        userInputPhone = phoneEntered
    }

    fun updateUserInputEmail(EmailEntered: String) {
        userInputEmail = EmailEntered
    }

    fun updateUserCountry(userCountryEntered: String) {
        userCountry = userCountryEntered
    }

    fun updateUserCity(userCityEntered: String) {
        userCity = userCityEntered
    }

    fun updateUserAddress(userAddressEntered: String) {
        userAddress = userAddressEntered
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return Pattern.matches(emailRegex, email)
    }

    private fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("[0-9]+"))
    }

    private fun updateInputState(
        currentState: ContactDataUiState,
        isInputNull: Boolean,
        inputFieldType: InputFieldType
    ): ContactDataUiState {
        return when (inputFieldType) {
            InputFieldType.PHONE -> currentState.copy(isInputPhoneNull = isInputNull)
            InputFieldType.EMAIL -> currentState.copy(isInputEmailNull = isInputNull)
            InputFieldType.COUNTRY -> currentState.copy(isInputCountryNull = isInputNull)
        }
    }

    fun checkUserInputs(navigateToContactData: () -> Unit) {

        _uiState.update { currentState ->
            updateInputState(
                currentState,
                !isValidPhone(userInputPhone),
                InputFieldType.PHONE
            )
        }

        _uiState.update { currentState ->
            updateInputState(
                currentState,
                !isValidEmail(userInputEmail),
                InputFieldType.EMAIL
            )
        }

        _uiState.update { currentState ->
            updateInputState(currentState, userCountry.isEmpty(), InputFieldType.COUNTRY)
        }

        if (!(_uiState.value.isInputPhoneNull) && !(_uiState.value.isInputEmailNull) && !(_uiState.value.isInputCountryNull)){
            printAllFields()
            navigateToContactData()
        }
    }

    private fun printAllFields(){
        Log.d("ContactDataViewModel", "phone: $userInputPhone")
        Log.d("ContactDataViewModel", "email: $userInputEmail")
        Log.d("ContactDataViewModel", "country: $userCountry")
        Log.d("ContactDataViewModel", "city: $userCity")
        Log.d("ContactDataViewModel", "address: $userAddress")
    }
}