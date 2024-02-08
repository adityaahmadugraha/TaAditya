package com.aditya.appsjeruk.user.ui.diagnosa

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa : ListAdapter<GejalaResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {

    private val rule1Value = 0.8
    private val rule2Value = 0.9
    private val rule3Value = 0.7
    private val rule4Value = 0.6
    private val rule5Value = 0.5
    private val rule6Value = 0.4
    private val rule7Value = 0.75
    private val rule8Value = 0.85

    // Properti untuk menyimpan nilai kepastian aturan 1
    var rule1Certainty: Double = 0.0
        private set // Membuat aksesor set private agar properti hanya bisa diubah di dalam kelas ini

    fun diagnosaPenyakit(): String {
        val selectedSymptoms = currentList.filter { it.isSelected }

        // Hitung tingkat kepastian untuk setiap aturan
        val rule1Certainty = calculateRuleCertainty(selectedSymptoms, listOf("G1", "G2")) * rule1Value
        val rule2Certainty = calculateRuleCertainty(selectedSymptoms, listOf("G3")) * rule2Value

        // Set nilai properti rule1Certainty di sini
        this.rule1Certainty = rule1Certainty

        val finalCertainty = (rule1Certainty + rule2Certainty) / 2

        val result = when {
            finalCertainty > 0.0 -> "P1"
            else -> "Tidak Diketahui"
        }

        Log.d("DiagnosisResult", "Diagnosis: $result $finalCertainty")

        return result
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

    private fun calculateRuleCertainty(
        selectedSymptoms: List<GejalaResponse>,
        gejalaCodes: List<String>
    ): Double {
        val relevantSymptoms = selectedSymptoms.filter { it.kodeGejala in gejalaCodes }
        return if (relevantSymptoms.isNotEmpty()) {
            val averageTingkatKepastian = relevantSymptoms.map { it.selectedTingkatKepastian }.average()
            val certaintyWeight = getCertaintyWeight(averageTingkatKepastian)
            averageTingkatKepastian * certaintyWeight
        } else {
            0.0
        }
    }

    private fun getCertaintyWeight(selectedTingkatKepastian: Double): Double {
        return when {
            selectedTingkatKepastian >= -0.8 && selectedTingkatKepastian <= 0.2 -> 1.0
            selectedTingkatKepastian >= 0.4 && selectedTingkatKepastian <= 1.0 -> 1.0
            else -> 0.0
        }
    }

    inner class ViewHolder(private val binding: ListDiagnosaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GejalaResponse) {
            binding.apply {
                cbFw.setOnCheckedChangeListener(null)
                cbFw.isChecked = data.isSelected
                cbFw.setOnCheckedChangeListener { _, isChecked ->
                    data.isSelected = isChecked
                    ilSpineer.visibility = if (isChecked) View.VISIBLE else View.GONE
                    if (!isChecked) {
                        etSpiner.text = null
                    }
                    if (isChecked) {
                        Log.d("SymptomSelected", "Selected symptom: ${data.namaGejala}")
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
                    data.selectedTingkatKepastian = when (options[position]) {
                        "Pasti Tidak" -> -0.2
                        "Hampir Pasti Tidak" -> -0.4
                        "Kemungkinan Besar Tidak" -> -0.6
                        "Mungkin Tidak" -> -0.8
                        "Tidak Tahu" -> 0.0
                        "Mungkin Iya" -> 0.4
                        "Kemungkinan Besar Iya" -> 0.6
                        "Hampir Pasti Iya" -> 0.8
                        "Pasti Iya" -> 1.0
                        else -> 0.0
                    }
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GejalaResponse> =
            object : DiffUtil.ItemCallback<GejalaResponse>() {
                override fun areItemsTheSame(oldItem: GejalaResponse, newItem: GejalaResponse): Boolean {
                    return oldItem.idGejala == newItem.idGejala
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: GejalaResponse, newItem: GejalaResponse): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
