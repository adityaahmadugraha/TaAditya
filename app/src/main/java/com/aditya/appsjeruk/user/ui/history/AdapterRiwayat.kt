package com.aditya.appsjeruk.user.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.databinding.ListRiwayatBinding

class AdapterRiwayat

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

        init {
            binding.icDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val riwayat = getItem(position)
                    onDeleteClickListener?.onDeleteClick(riwayat)
                }
            }
        }

        fun bind(data: Riwayat) {
            binding.apply {
                tvTanggal.text = data.tgl_diagnosa
                tvHasil.text = data.hasil_diagnosa
            }
        }
    }


    interface OnDeleteClickListener {
        fun onDeleteClick(riwayat: Riwayat)
    }

    private var onDeleteClickListener: OnDeleteClickListener? = null

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
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