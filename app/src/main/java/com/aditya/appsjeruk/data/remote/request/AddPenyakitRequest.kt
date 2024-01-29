package com.aditya.appsjeruk.data.remote.request

import com.google.gson.annotations.SerializedName

data class AddPenyakitRequest(

    @field:SerializedName("kode")
    val kode: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("pecegahan")
    val pecegahan: String?,

    @field:SerializedName("foto")
    val foto: String?,
)
