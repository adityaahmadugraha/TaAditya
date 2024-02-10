package com.aditya.appsjeruk.user.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.remote.request.Login
import com.aditya.appsjeruk.databinding.ListPenyakitBinding
import com.aditya.appsjeruk.databinding.ListRiwayatBinding
import com.aditya.appsjeruk.user.ui.diagnosa.GejalaResponse
import com.bumptech.glide.Glide

class AdapterRiwayat
//    (
//    private val onItemClick: (GejalaResponse) -> Unit
//)
    : ListAdapter<Riwayat, AdapterRiwayat.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListRiwayatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Riwayat) {
            binding.apply {
                tvTanggal.text = data.tgl_diagnosa
                tvHasil.text = data.nama_penyakit

            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Riwayat> =
            object : DiffUtil.ItemCallback<Riwayat>() {
                override fun areItemsTheSame(
                    oldItem: Riwayat,
                    newItem: Riwayat
                ): Boolean {
                    return oldItem.id_riwayat == newItem.id_riwayat
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: Riwayat,
                    newItem: Riwayat
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}