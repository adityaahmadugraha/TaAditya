package com.aditya.appsjeruk.admin.riwayat_admin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.databinding.ListRiwayatAllBinding
import com.aditya.appsjeruk.databinding.ListRiwayatBinding
import com.aditya.appsjeruk.user.ui.history.Riwayat

class AdapterRiwayatAll
//    (
//    private val onItemClick: (GejalaResponse) -> Unit
//)
    : ListAdapter<Riwayat, AdapterRiwayatAll.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListRiwayatAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListRiwayatAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Riwayat) {
            binding.apply {
                tvTanggal.text = data.tgl_diagnosa
                tvHasil.text = data.hasil_diagnosa
                tvUser.text = data.nama_user
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