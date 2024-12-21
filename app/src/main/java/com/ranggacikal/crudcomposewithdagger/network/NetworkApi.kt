package com.ranggacikal.crudcomposewithdagger.network

import com.ranggacikal.crudcomposewithdagger.model.RegisterData
import com.ranggacikal.crudcomposewithdagger.model.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkApi {
    @POST("register-user")
    suspend fun postRegister(@Body request: RegisterRequest) : RegisterData
}