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
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa(
    private val onItemClick: (String) -> Unit
) : ListAdapter<GejalaResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {

    fun diagnosaPenyakit(): String {
        val selectedSymptoms = currentList.filter { it.isSelected }

        // Rule 1
        val hasG1 = selectedSymptoms.any { it.kodeGejala == "G1" }
        val hasG2 = selectedSymptoms.any { it.kodeGejala == "G2" }
        val rule1 = hasG1 && hasG2

        // Rule 2
        val hasG3 = selectedSymptoms.any { it.kodeGejala == "G3" }
        val rule2 = hasG3

        // Rule 3
        val hasG4 = selectedSymptoms.any { it.kodeGejala == "G4" }
        val hasG5 = selectedSymptoms.any { it.kodeGejala == "G5" }
        val rule3 = hasG4 && hasG5

        // Rule 4
        val rule4 = selectedSymptoms.any { it.kodeGejala == "G6" }

        // Rule 5
        val rule5 = selectedSymptoms.any { it.kodeGejala == "G7" }

        // Rule 6
        val rule6 = selectedSymptoms.any { it.kodeGejala == "G8" }

        // Rule 7
        val hasG9 = selectedSymptoms.any { it.kodeGejala == "G9" }
        val hasG12 = selectedSymptoms.any { it.kodeGejala == "G12" }
        val rule7 = hasG9 && hasG12

        // Rule 8
        val hasG10 = selectedSymptoms.any { it.kodeGejala == "G10" }
        val rule8 = hasG10

        // Rule 9
        val hasG11 = selectedSymptoms.any { it.kodeGejala == "G11" }
        val rule9 = hasG11

        // Rule 10
        val hasG13 = selectedSymptoms.any { it.kodeGejala == "G13" }
        val hasG15 = selectedSymptoms.any { it.kodeGejala == "G15" }
        val hasG18 = selectedSymptoms.any { it.kodeGejala == "G18" }
        val rule10 = hasG13 && hasG15 && hasG18

        // Rule 11
        val hasG14 = selectedSymptoms.any { it.kodeGejala == "G14" }
        val rule11 = hasG14

        // Rule 12
        val hasG16 = selectedSymptoms.any { it.kodeGejala == "G16" }
        val rule12 = hasG16

        // Rule 13
        val hasG17 = selectedSymptoms.any { it.kodeGejala == "G17" }
        val rule13 = hasG17

        // Rule 14
        val hasG19 = selectedSymptoms.any { it.kodeGejala == "G19" }
        val hasG22 = selectedSymptoms.any { it.kodeGejala == "G22" }
        val hasG23 = selectedSymptoms.any { it.kodeGejala == "G23" }
        val rule14 = hasG19 && hasG22 && hasG23

        // Rule 15
        val hasG20 = selectedSymptoms.any { it.kodeGejala == "G20" }
        val rule15 = hasG20

        // Rule 16
        val hasG21 = selectedSymptoms.any { it.kodeGejala == "G21" }
        val rule16 = hasG21

        // Check rules
        return when {
            rule1 && rule2 -> "P1"
            rule3 -> "P2"
            rule4 && rule5 && rule6 -> "P3"
            rule7 && rule8 && rule9 -> "P4"
            rule10 && rule11 && rule12 && rule13 -> "P5"
            rule14 && rule15 && rule16 -> "P6"
            else -> "Tidak Diketahui"
        }
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