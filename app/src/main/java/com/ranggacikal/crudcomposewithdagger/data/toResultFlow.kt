package com.ranggacikal.crudcomposewithdagger.data

import android.content.Context
import com.ranggacikal.crudcomposewithdagger.utils.Constant.API_FAILED_CODE
import com.ranggacikal.crudcomposewithdagger.utils.Constant.API_INTERNET_CODE
import com.ranggacikal.crudcomposewithdagger.utils.Constant.API_INTERNET_MESSAGE
import com.ranggacikal.crudcomposewithdagger.utils.NetWorkResult
import com.ranggacikal.crudcomposewithdagger.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import kotlin.reflect.KMutableProperty

inline fun <reified T> toResultFlow(context: Context, crossinline call: suspend () -> Response<T>?): Flow<NetWorkResult<T>> {
    return flow {
        val isInternetConnected = Utils.hasInternetConnection(context)
        if (isInternetConnected) {
            emit(NetWorkResult.Loading( true))
            val c = call()
            c?.let { response ->
                try {
                    if (c.isSuccessful && c.body()!=null) {
                        c.body()?.let {
                            emit(NetWorkResult.Success(it))
                        }
                    } else {
                        val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(), response.code().toString(), response.message())
                        emit(NetWorkResult.Error(model, response.message()))
                    }
                } catch (e: Exception) {
                    val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(), API_FAILED_CODE, e.message)
                    emit(NetWorkResult.Error(model, e.toString()))
                }
            }
        } else {
            val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(),
                API_INTERNET_CODE, API_INTERNET_MESSAGE)
            emit(NetWorkResult.Error(model, API_INTERNET_MESSAGE))
        }
    }.flowOn(Dispatchers.IO)
}

inline fun <reified T> setResponseStatus(instance: T?, errorCode: String?, message: String?):T? {
    return try {
        instance?.let {
            val properties = it::class.members
            for (property in properties) {
                if (property is KMutableProperty<*>) {
                    when (property.name) {
                        "ErrorCode" -> (property as KMutableProperty<*>).setter.call(instance, errorCode)
                        "Message" -> (property as KMutableProperty<*>).setter.call(instance, message)
                    }
                }
            }
        }
        instance
    } catch (e: Exception) {
        null
    }
}