package com.ranggacikal.crudcomposewithdagger.screens

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranggacikal.crudcomposewithdagger.base.BaseViewModel
import com.ranggacikal.crudcomposewithdagger.data.DataOrException
import com.ranggacikal.crudcomposewithdagger.data.toResultFlow
import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.repository.RegisterRepository
import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING
import com.ranggacikal.crudcomposewithdagger.utils.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository,
    private val application: Application
) : BaseViewModel(application = application) {
    var username: String = EMPTY_STRING
    var email: String = EMPTY_STRING

    private val _postRegisterData: MutableLiveData<NetWorkResult<RegisterData>> = MutableLiveData()
    val postRegisterData: LiveData<NetWorkResult<RegisterData>> = _postRegisterData

    fun postRegister(request: RegisterRequest) = viewModelScope.launch {
        repository.postRegister(application, request).collect { registerData ->
            _postRegisterData.value = registerData
        }
    }

}