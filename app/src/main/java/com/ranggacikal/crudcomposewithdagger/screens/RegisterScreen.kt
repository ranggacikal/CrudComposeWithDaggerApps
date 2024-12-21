package com.ranggacikal.crudcomposewithdagger.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ranggacikal.crudcomposewithdagger.component.CircularLoading
import com.ranggacikal.crudcomposewithdagger.component.CustomTextField
import com.ranggacikal.crudcomposewithdagger.component.PrimaryButton
import com.ranggacikal.crudcomposewithdagger.utils.AppColors

@Composable
fun RegisterScreen(
    onClick: () -> Unit,
    onTextChangeUsername: (String) -> Unit,
    onTextChangeEmail: (String) -> Unit,
) {
    Column(modifier = Modifier.background(AppColors.mOffWhite)) {
        CustomTextField(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            label = "Email",
            placeholder = "Email",
            onImeAction = {},
            onTextChange = onTextChangeUsername
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            label = "Username",
            placeholder = "Username",
            onImeAction = {},
            onTextChange = onTextChangeEmail
        )

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 12.dp),
            text = "REGISTER",
            onClick = onClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteScreen() {
    RegisterScreen({}, {}, {})
}