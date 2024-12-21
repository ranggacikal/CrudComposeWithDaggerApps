package com.ranggacikal.crudcomposewithdagger.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranggacikal.crudcomposewithdagger.data.DataOrException
import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.repository.RegisterRepository
import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    var username: String = EMPTY_STRING
    var email: String = EMPTY_STRING

    private val _data = MutableLiveData<DataOrException<RegisterData, Boolean, Exception>>(
        DataOrException(null, true, Exception(""))
    )

    val data: LiveData<DataOrException<RegisterData, Boolean, Exception>> = _data

    fun postRegister(request: RegisterRequest) {
        viewModelScope.launch {
            _data.value = DataOrException(null, true, null)
            val response = repository.postDataRegister(request)
            _data.value = response
            if (response.data?.data.toString().isNotEmpty()) {
                _data.value = _data.value?.copy(isLoading = false)
            }
        }
    }

}