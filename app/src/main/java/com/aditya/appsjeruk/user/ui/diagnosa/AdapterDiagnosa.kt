package com.aditya.appsjeruk.user.ui.diagnosa


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa
    (
    private val onItemClick: (PenyakitResponse) -> Unit
) : ListAdapter<PenyakitResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListDiagnosaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListDiagnosaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PenyakitResponse) {
            binding.apply {
                cbFw.setOnCheckedChangeListener { _, isChecked ->
                    radioGroup.visibility = if (isChecked) View.VISIBLE else View.GONE
                }
                tvTitle.text = data.nama
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
                    return oldItem.id == newItem.id
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