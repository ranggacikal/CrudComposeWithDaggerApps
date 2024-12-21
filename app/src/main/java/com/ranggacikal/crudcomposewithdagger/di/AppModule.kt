package com.ranggacikal.crudcomposewithdagger.di

import com.ranggacikal.crudcomposewithdagger.network.NetworkApi
import com.ranggacikal.crudcomposewithdagger.repository.RegisterRepository
import com.ranggacikal.crudcomposewithdagger.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRegisterRepository(api: NetworkApi): RegisterRepository {
        return RegisterRepository(api)
    }

    @Provides
    @Singleton
    fun provideQuestionApi(): NetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

}