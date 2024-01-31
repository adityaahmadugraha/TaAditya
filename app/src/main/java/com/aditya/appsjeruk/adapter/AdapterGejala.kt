package com.aditya.appsjeruk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import com.aditya.appsjeruk.databinding.ListPenyakitBinding
import com.bumptech.glide.Glide

class AdapterGejala
    (
    private val onItemClick: (PenyakitResponse) -> Unit
) : ListAdapter<PenyakitResponse, AdapterGejala.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListPenyakitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListPenyakitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PenyakitResponse) {
            binding.apply {
                tvNama.text = data.namaGejala
                tvDeskripsi.text = data.deskripsiGejala
                Glide.with(itemView.context)
                    .load(data.fotoGejala)
                    .error(R.color.blue)
                    .into(foto)
                itemView.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PenyakitResponse> =
            object : DiffUtil.ItemCallback<PenyakitResponse>() {
                override fun areItemsTheSame(
                    oldItem: PenyakitResponse,
                    newItem: PenyakitResponse
                ): Boolean {
                    return oldItem.idGejala == newItem.idGejala
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: PenyakitResponse,
                    newItem: PenyakitResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}