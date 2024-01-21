package com.aditya.appsjeruk.user.ui.detailstatuslaporan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityDetailStatusLaporanBinding
import com.aditya.appsjeruk.user.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStatusLaporanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailStatusLaporanBinding
    private val viewModel: DetailViewModel by viewModels()
    var id: String = ""

    companion object {
        const val TAG_BUNDLE = "kode"
        const val TAG_TIPE = "tipe"
        const val TAG_TANGGAL = "tanggal"
        const val TAG_LOKASI = "lokasi"
        const val TAG_STATUS = "status"
        const val TAG_FOTO = "foto"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStatusLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        id = intent.getStringExtra("ID_LAPORAN").toString()


        binding.imgBackStatusLaporan.setOnClickListener {
            intent = Intent(this@DetailStatusLaporanActivity, MainActivity::class.java)
            startActivity(intent)
        }


        val bundle = intent.getBundleExtra(TAG_BUNDLE)
        if (bundle != null) {
            binding.apply {

                etType.text = Editable.Factory.getInstance().newEditable(bundle.getString(TAG_TIPE))

                etTanggal.text = Editable.Factory.getInstance().newEditable(bundle.getString(
                    TAG_TANGGAL
                ))

                etLokasi.text = Editable.Factory.getInstance().newEditable(bundle.getString(
                    TAG_LOKASI
                ))

                tvStatus.text = Editable.Factory.getInstance().newEditable(bundle.getString(
                    TAG_STATUS
                ))





                btnBack.setOnClickListener {
                    finish()
                }
            }
        }

//        getDataLaporan()
    }




}