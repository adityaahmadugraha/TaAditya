package com.aditya.appsjeruk.data.remote.request

data class Login(
    val status: Boolean,
    val data: UserData ,
    val message :String,
)

data class UserData(
    val id: String,
    val nama: String,
    val username: String,
//    val password: String,
    val roles: String,
//    val email : String,
)
