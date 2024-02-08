package com.aditya.appsjeruk.user.ui.diagnosa

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
    val deskripsiGejala: String? = null,

    @field:SerializedName("nilai_cf")
    val nilaiCf: String? = null,

    var isSelected: Boolean = false,

    var selectedTingkatKepastian: Double = 0.0,

//    var isSelected: Boolean = false,
//    var selectedTingkatKepastian: Double = 0.0,
    val nilaiCfPakar: Double

)
