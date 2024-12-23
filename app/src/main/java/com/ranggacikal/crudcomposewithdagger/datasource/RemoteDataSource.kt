package com.ranggacikal.crudcomposewithdagger.datasource

import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.network.NetworkApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApi) {

    suspend fun postRegister(registerRequest: RegisterRequest) =
        apiService.postRegister(registerRequest)

}