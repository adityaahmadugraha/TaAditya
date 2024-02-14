package com.aditya.appsjeruk.data.remote.request

import com.google.gson.annotations.SerializedName

data class DiagnosaRequest(
    val gejala: List<String>,
    val tingkatKepastian: List<Double>

)

data class DiagnosaResponse(


    val namaPenyakit: String?,

    @field:SerializedName("nilai_cf")
    val nilaiCf: String? = null,

    var isSelected: Boolean = false,

    var selectedTingkatKepastian: Double = 0.0,

    @field:SerializedName("kode_gejala")
    val kodeGejala: String? = null,

    @field:SerializedName("nama_gejala")
    val namaGejala: String? = null,

    @field:SerializedName("id_gejala")
    val idGejala: String? = null,

    @field:SerializedName("foto_gejala")
    val fotoGejala: String? = null,

    @field:SerializedName("deskripsi_gejala")
    val deskripsiGejala: String? = null,


    @field:SerializedName("pertanyaan")
    val pertanyaan: String? = null,



)

