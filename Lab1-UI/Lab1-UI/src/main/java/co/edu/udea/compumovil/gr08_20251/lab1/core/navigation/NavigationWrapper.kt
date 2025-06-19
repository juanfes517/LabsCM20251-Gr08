package co.edu.udea.compumovil.gr08_20251.lab1.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr08_20251.lab1.ui.ContactDataScreen
import co.edu.udea.compumovil.gr08_20251.lab1.ui.PersonalDataScreen
import co.edu.udea.compumovil.gr08_20251.lab1.ui.ResetScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PersonalData) {
        composable<PersonalData> {
            PersonalDataScreen{ navController.navigate(ContactData) }
        }

        composable<ContactData> {
            ContactDataScreen{ navController.navigate(Reset) }
        }

        composable<Reset> {
            ResetScreen{ navController.navigate(PersonalData) {
                popUpTo<PersonalData>{ inclusive = true }
            } }
        }
    }
}