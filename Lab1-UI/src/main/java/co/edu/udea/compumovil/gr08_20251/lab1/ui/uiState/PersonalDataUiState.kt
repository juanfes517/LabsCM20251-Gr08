package co.edu.udea.compumovil.gr08_20251.lab1.ui.uiState

data class PersonalDataUiState (
    val inputName: String = "",
    val inputLastName: String = "",
    val inputGender: String = "",
    val inputBirthday: Long? = null,
    val inputEducationLevel: String = "",

    val isInputNameNull: Boolean = false,
    val isInputLastNameNull: Boolean = false,
    val isInputBirthdayNull: Boolean = false,
)
