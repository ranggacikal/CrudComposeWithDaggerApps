package com.ranggacikal.crudcomposewithdagger.model.request

import com.ranggacikal.crudcomposewithdagger.utils.Constant.EMPTY_STRING

data class RegisterRequest(
    val userId: Int = 0,
    val username: String = EMPTY_STRING,
    val email: String = EMPTY_STRING
)
