package com.aditya.appsjeruk.user.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import com.aditya.appsjeruk.user.ui.diagnosa.GejalaResponse
import com.aditya.appsjeruk.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: AdminViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetail()
        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityDetail, ActivityAdmin::class.java)
            startActivity(intent)
        }
        binding.fabDelete.setOnClickListener {
            showDeleteConfirmationDialog()

        }
    }

    private fun getDetail() {
        val title = intent.getStringExtra("title").toString()
        val deskripsi = intent.getStringExtra("deskripsi").toString()
//        val pencegahan = intent.getStringExtra("pencegahan").toString()
        val image = intent.getStringExtra("foto").toString()
        val id = intent.getStringExtra("id_penyakit").toString()


        val tvName = binding.tvName
        tvName.text = title

//        val tvPencegahan = binding.tvPencegahan
//        tvPencegahan.text = pencegahan

        val tvDeskripsi = binding.tvDeskripsi
        tvDeskripsi.text = deskripsi




        Glide.with(this)
            .load(BuildConfig.IMAGE_URL_GEJALA + image)
            .error(R.drawable.error)
            .into(binding.imgDetail)
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Hapus Data")
            .setMessage("Apakah Anda yakin menghapus data ini?")
            .setPositiveButton("Ya") { _, _ ->

                deletePenyakit()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun deletePenyakit() {
        val itemRequest = PenyakitResponse(
            idPenyakit = intent.getStringExtra("id_penyakit").toString()
        )
        viewModel.deletePenyakit(itemRequest.idPenyakit.toString()).observe(this) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    finish()
                }

                is Resource.Error -> {
                    Toast.makeText(this, "Gagal menghapus penyakit", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
