package com.ranggacikal.crudcomposewithdagger.repository

import android.util.Log
import com.ranggacikal.crudcomposewithdagger.data.DataOrException
import com.ranggacikal.crudcomposewithdagger.model.Data
import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.network.NetworkApi
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: NetworkApi) {

    private val dataOrException = DataOrException<RegisterData, Boolean, Exception>()

    suspend fun postDataRegister(request: RegisterRequest): DataOrException<RegisterData, Boolean, Exception> {
        try {
            dataOrException.isLoading = true
            dataOrException.data = api.postRegister(request)
            if (dataOrException.data?.data.toString().isNotEmpty()) dataOrException.isLoading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exc", "postRegister: ${dataOrException.e?.localizedMessage}")
        }
        return dataOrException
    }

}