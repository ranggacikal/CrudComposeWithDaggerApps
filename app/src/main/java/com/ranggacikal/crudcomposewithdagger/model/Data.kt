package com.ranggacikal.crudcomposewithdagger.model

data class Data(
    val created_at: String,
    val email: String,
    val token: String,
    val user_id: Int,
    val username: String
)