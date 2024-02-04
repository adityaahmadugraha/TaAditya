package com.aditya.appsjeruk.user.ui.diagnosa


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.remote.response.GejalaResponse
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa(
    private val onItemClick: (String) -> Unit
) : ListAdapter<GejalaResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {

    fun diagnosaPenyakit(): String {
        val selectedSymptoms = currentList.filter { it.isSelected }

        // Rule 1
        if (selectedSymptoms.any { it.kodeGejala == "G1" } && selectedSymptoms.any { it.kodeGejala == "G2" }) {
            return "P1"
        }

        // Rule 2
        if (selectedSymptoms.any { it.kodeGejala == "G3" }) {
            return "G2"
        }

        // Rule 3
        if (selectedSymptoms.any { it.kodeGejala == "G4" } && selectedSymptoms.any { it.kodeGejala == "G5" }) {
            return "P2"
        }

        // Rule 4
        if (selectedSymptoms.any { it.kodeGejala == "G6" }) {
            return "P3"
        }

        // Rule 5
        if (selectedSymptoms.any { it.kodeGejala == "G7" }) {
            return "G6"
        }

        // Rule 6
        if (selectedSymptoms.any { it.kodeGejala == "G8" }) {
            return "G7"
        }

        // Rule 7
        if (selectedSymptoms.any { it.kodeGejala == "G9" } && selectedSymptoms.any { it.kodeGejala == "G12" }) {
            return "P4"
        }

        return "Tidak Diketahui"
    }

    fun getSelectedSymptoms(): List<GejalaResponse> {
        return currentList.filter { it.isSelected }
    }

    fun getSelectedTingkatKepastian(): List<GejalaResponse> {
        return currentList.filter { it.isSelected }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListDiagnosaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                    data.isSelected = isChecked
                    ilSpineer.visibility = if (isChecked) View.VISIBLE else View.GONE
                    if (!isChecked) {
                        etSpiner.text = null
                    }
                }
                tvTitle.text = data.namaGejala
                val options = listOf(
                    "Pasti Tidak",
                    "Hampir Pasti Tidak",
                    "Kemungkinan Besar Tidak",
                    "Mungkin Tidak",
                    "Tidak Tahu",
                    "Mungkin Iya",
                    "Kemungkinan Besar Iya",
                    "Hampir Pasti Iya",
                    "Pasti Iya"
                )

                val adapter = ArrayAdapter(itemView.context, R.layout.dropdown_item, options)
                etSpiner.setAdapter(adapter)

                etSpiner.setOnItemClickListener { _, _, position, _ ->
                    data.selectedTingkatKepastian = position.toDouble() / options.size.toDouble()
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