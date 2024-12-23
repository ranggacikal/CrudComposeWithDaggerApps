package com.ranggacikal.crudcomposewithdagger.model

import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING

data class RegisterData(
    val data: Data? = null,
    val message: String = EMPTY_STRING,
    val status: String = EMPTY_STRING
)