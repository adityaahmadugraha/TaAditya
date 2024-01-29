package com.aditya.appsjeruk.data.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("roles")
    val roles: String
)
