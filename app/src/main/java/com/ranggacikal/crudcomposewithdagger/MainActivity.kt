package com.ranggacikal.crudcomposewithdagger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.ranggacikal.crudcomposewithdagger.data.DataOrException
import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.screens.RegisterScreen
import com.ranggacikal.crudcomposewithdagger.screens.RegisterViewModel
import com.ranggacikal.crudcomposewithdagger.ui.theme.CrudComposeWithDaggerAppsTheme
import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrudComposeWithDaggerAppsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: RegisterViewModel) {
    ObserveDataRegister(viewModel)
    Column {
        RegisterScreen(
            onClick = {
                viewModel.postRegister(
                    request = RegisterRequest(
                        userId = Random.nextInt(1000, 9999),
                        username = viewModel.username,
                        email = viewModel.email
                    )
                )
            },
            onTextChangeUsername = { username ->
                viewModel.username = username
            },
            onTextChangeEmail = { email ->
                viewModel.email = email
            }
        )
    }
}

@Composable
fun ObserveDataRegister(viewModel: RegisterViewModel) {
    val dataRegister by viewModel.data.observeAsState(
        DataOrException(
            null,
            true,
            Exception(EMPTY_STRING)
        )
    )
    if (dataRegister.isLoading == true) {
        Log.d("RegisterTest", "ObserveDataRegister: LOADING.......")
    } else {
        Log.d("RegisterTest", "ObserveDataRegister: LOADING STOPPED.......")
        Log.d("RegisterTest", "ObserveDataRegister: data ${dataRegister.data}")
    }
}