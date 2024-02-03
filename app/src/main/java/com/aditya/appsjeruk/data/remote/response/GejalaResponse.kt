package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class GejalaResponse(

    @field:SerializedName("kode_gejala")
    val kodeGejala: String? = null,

    @field:SerializedName("nama_gejala")
    val namaGejala: String? = null,

    @field:SerializedName("id_gejala")
    val idGejala: String? = null,

    @field:SerializedName("foto_gejala")
    val fotoGejala: String? = null,

    @field:SerializedName("deskripsi_gejala")
    val deskripsiGejala: String? = null
)
