package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: UserResponse? = null,

    @field:SerializedName("status")
    val status: Boolean? = null,
    )
