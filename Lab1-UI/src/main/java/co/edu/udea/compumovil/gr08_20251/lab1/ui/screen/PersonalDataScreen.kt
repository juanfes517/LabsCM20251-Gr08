package co.edu.udea.compumovil.gr08_20251.lab1.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.R
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.CustomIcon
import co.edu.udea.compumovil.gr08_20251.lab1.ui.common.Header
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookBold
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookRegular

@Composable
fun PersonalDataScreen(navigateToContactData: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                //.background(Color.Blue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            Header("Información personal")
            Form(navigateToContactData)

        }
    }
}

@Composable
fun Form(navigateToContactData: () -> Unit) {
    var text by remember { mutableStateOf("") }

    CustomTextField(
        userInput = text,
        onUserInputChanged = {},
        iconId = R.drawable.person,
        labelText = "Nombre",
        keyboardCapitalization = KeyboardCapitalization.Sentences,
        keyboardType = KeyboardType.Text
    )

    CustomTextField(
        userInput = text,
        onUserInputChanged = {  },
        iconId = R.drawable.person_add,
        labelText = "Apellidos",
        keyboardCapitalization = KeyboardCapitalization.Sentences,
        keyboardType = KeyboardType.Text
    )
}

@Composable
fun CustomTextField(
    userInput: String,
    onUserInputChanged: (String) -> Unit,
    iconId: Int,
    labelText: String,
    keyboardCapitalization: KeyboardCapitalization,
    keyboardType: KeyboardType
) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
    ) {
        CustomIcon(
            modifier = Modifier.offset(y = (5).dp),
            iconId = iconId,
            iconSize = 40.dp
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = userInput,
                onValueChange = onUserInputChanged ,
                label = {
                    Text(
                        text = labelText,
                        style = TextStyle(
                            fontFamily = balinookBold,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                },
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
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(top = 0.dp)
                    .width(250.dp)
            )
        }
    }
}