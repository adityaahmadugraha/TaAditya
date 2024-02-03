package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class PenyakitResponse(

    @field:SerializedName("deskripsi_penyakit")
    val deskripsiPenyakit: String? = null,

    @field:SerializedName("nama_penyakit")
    val namaPenyakit: String? = null,

    @field:SerializedName("kode_penyakit")
    val kodePenyakit: String? = null,

    @field:SerializedName("id_penyakit")
    val idPenyakit: String? = null,

    @field:SerializedName("pencegahan")
    val pencegahan: String? = null,

    @field:SerializedName("foto_penyakit")
    val fotoPenyakit: String? = null

)
