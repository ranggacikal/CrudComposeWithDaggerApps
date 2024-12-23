package com.ranggacikal.crudcomposewithdagger.repository

import android.content.Context
import android.util.Log
import com.ranggacikal.crudcomposewithdagger.data.DataOrException
import com.ranggacikal.crudcomposewithdagger.data.toResultFlow
import com.ranggacikal.crudcomposewithdagger.datasource.RemoteDataSource
import com.ranggacikal.crudcomposewithdagger.model.Data
import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import com.ranggacikal.crudcomposewithdagger.network.NetworkApi
import com.ranggacikal.crudcomposewithdagger.utils.NetWorkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun postRegister(
        context: Context,
        registerRequest: RegisterRequest
    ): Flow<NetWorkResult<RegisterData>> {
        return toResultFlow(context) {
            remoteDataSource.postRegister(registerRequest)
        }
    }

}