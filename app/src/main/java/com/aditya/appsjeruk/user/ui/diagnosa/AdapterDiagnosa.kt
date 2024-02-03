package com.aditya.appsjeruk.user.ui.diagnosa


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.data.remote.response.GejalaResponse
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa
    (
    private val onItemClick: (GejalaResponse) -> Unit
) : ListAdapter<GejalaResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {


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
        fun bind(data: GejalaResponse) {
            binding.apply {
                cbFw.setOnCheckedChangeListener { _, isChecked ->
                    radioGroup.visibility = if (isChecked) View.VISIBLE else View.GONE
                }
                tvTitle.text = data.namaGejala
                itemView.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GejalaResponse> =
            object : DiffUtil.ItemCallback<GejalaResponse>() {
                override fun areItemsTheSame(
                    oldItem: GejalaResponse,
                    newItem: GejalaResponse
                ): Boolean {
                    return oldItem.idGejala == newItem.idGejala
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: GejalaResponse,
                    newItem: GejalaResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}