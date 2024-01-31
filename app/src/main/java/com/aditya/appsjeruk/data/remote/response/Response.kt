package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("kode_gejala")
	val kodeGejala: String? = null,

	@field:SerializedName("nama_gejala")
	val namaGejala: String? = null,

	@field:SerializedName("id_gejala")
	val idGejala: Int? = null,

	@field:SerializedName("foto_gejala")
	val fotoGejala: String? = null,

	@field:SerializedName("deskripsi_gejala")
	val deskripsiGejala: String? = null
)
