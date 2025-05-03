package co.edu.udea.compumovil.gr08_20251.lab1.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20251.lab1.R
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookBold
import co.edu.udea.compumovil.gr08_20251.lab1.ui.theme.balinookRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(title: String) {
    Column {
        TopAppBar(
            title = { Text(title) },
            modifier = Modifier.height(60.dp)
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 0.dp)
        )
    }
}

// example of icon id: R.drawable.person
@Composable
fun CustomIcon(modifier: Modifier = Modifier, iconId: Int, iconSize: Dp) {
    Image(
        painter = painterResource(iconId),
        contentDescription = null,
        modifier = modifier.size(iconSize),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun CustomNextButton(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 30.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onClickButton,
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor  = Color.Transparent,
            ),
            modifier = modifier.fillMaxWidth(0.4f)
        ) {
            Text(
                text = stringResource(R.string.next),
                fontFamily = balinookBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun CustomTextField(
    userInput: String,
    onUserInputChanged: (String) -> Unit,
    iconId: Int,
    labelText: String,
    keyboardCapitalization: KeyboardCapitalization,
    keyboardType: KeyboardType,
    isInputNull: Boolean,
    modifier: Modifier = Modifier,
    imeAction: ImeAction
) {

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
            TextField(
                value = userInput,
                onValueChange = onUserInputChanged,
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
                    imeAction = imeAction
                )
            )
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