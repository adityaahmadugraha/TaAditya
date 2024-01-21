package com.aditya.appsjeruk.data.remote.request

data class Login(
    val status: Boolean,
    val data: UserData ,
    val message :String,
)

data class UserData(
    val id: String,
    val name: String,
    val username: String,
    val roles: String
)
