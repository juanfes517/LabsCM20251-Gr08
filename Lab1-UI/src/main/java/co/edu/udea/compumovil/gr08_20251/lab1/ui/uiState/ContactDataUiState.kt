package co.edu.udea.compumovil.gr08_20251.lab1.ui.uiState

data class ContactDataUiState(
    val isInputPhoneNull: Boolean = false,
    val isInputEmailNull: Boolean = false,
    val isInputCountryNull: Boolean = false,
)

enum class InputFieldType {
    PHONE, EMAIL, COUNTRY
}