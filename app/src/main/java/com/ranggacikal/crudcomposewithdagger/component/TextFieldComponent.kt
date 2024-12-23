package com.ranggacikal.crudcomposewithdagger.component

import android.text.InputType
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.ranggacikal.crudcomposewithdagger.utils.AppColors
import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    var text by remember { mutableStateOf(EMPTY_STRING) }
    val keyboardCotroller = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = {
            text = it
            onTextChange(it)
        },
        modifier = modifier,
        label = { Text(text = label) } ,
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.textFieldColors(AppColors.mBlack),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardCotroller?.hide()
        })

    )
}