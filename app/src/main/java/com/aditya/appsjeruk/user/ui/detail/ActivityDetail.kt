package com.aditya.appsjeruk.user.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
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

        binding.fabDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun getDetail() {
        val title = intent.getStringExtra("title").toString()
        val image = intent.getStringExtra("image").toString()
        val deskripsi = intent.getStringExtra("deskripsi").toString()
//        val pencegahan = intent.getStringExtra("pencegahan").toString()

        val tvDeskripsi = binding.tvName
        tvDeskripsi.text = title

//        val tvPencegahan = binding.tvPencegahan
//        tvPencegahan.text = title

        val tvName = binding.tvDeskripsi
        tvName.text = deskripsi
        Glide.with(this)
            .load(image)
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
        val itemRequest = GejalaResponse(
            idGejala = intent.getStringExtra("id_gejala").toString()
        )
        viewModel.deletePenyakit(itemRequest.idGejala.toString()).observe(this) { result ->
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
