package com.aditya.appsjeruk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import com.aditya.appsjeruk.databinding.ListBeritaBinding
import com.bumptech.glide.Glide

class AdapterGejala
//    (
//    private val onItemClick: (PenyakitResponse) -> Unit
//)
    : ListAdapter<PenyakitResponse, AdapterGejala.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListBeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PenyakitResponse) {
            binding.apply {
                tvTitleBerita.text = data.type
                Glide.with(itemView.context)
                    .load(data.foto)
                    .error(R.color.blue)
                    .into(imgBerita)
//                itemView.setOnClickListener {
//                    onItemClick(data)
//                }
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
                    return oldItem.idPenyakit == newItem.idPenyakit
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