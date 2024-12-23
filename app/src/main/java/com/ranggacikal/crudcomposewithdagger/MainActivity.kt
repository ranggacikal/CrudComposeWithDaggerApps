package com.ranggacikal.crudcomposewithdagger

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.screens.RegisterScreen
import com.ranggacikal.crudcomposewithdagger.screens.RegisterViewModel
import com.ranggacikal.crudcomposewithdagger.ui.theme.CrudComposeWithDaggerAppsTheme
import com.ranggacikal.crudcomposewithdagger.utils.NetWorkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ObserveDataRegister(viewModel)
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
    Column {
        RegisterScreen(
            onClick = {
                try {
                    viewModel.postRegister(
                        request = RegisterRequest(
                            userId = Random.nextInt(1000, 9999),
                            username = viewModel.username,
                            email = viewModel.email
                        )
                    )
                } catch (e: Exception) {
                    e.stackTrace
                }
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
    val dataRegister = viewModel.postRegisterData.observeAsState()
    when (val resultRegister = dataRegister.value) {
        is NetWorkResult.Loading -> {
            Toast.makeText(LocalContext.current, "Loading State", Toast.LENGTH_SHORT).show()
            Log.d("registerTest", "ObserveDataRegister: LOADING....")
        }

        is NetWorkResult.Error -> {
            Log.d("registerTest", "ObserveDataRegister: LOADING STOP.... ERROR....")
            Log.d("registerTest", "ObserveDataRegister: ${resultRegister.message}")
            Toast.makeText(LocalContext.current, "Error State", Toast.LENGTH_SHORT).show()
        }

        is NetWorkResult.Success -> {
            val successData = resultRegister.data?.data
            Toast.makeText(
                LocalContext.current,
                "Success: \n name : ${successData?.username}, \n Email : ${successData?.email}",
                Toast.LENGTH_LONG
            ).show()
            Log.d("registerTest", "ObserveDataRegister: $successData")
        }

        null -> {
            Log.d("registerTest", "ObserveDataRegister: NULL EXCEPTION")
        }
    }
}