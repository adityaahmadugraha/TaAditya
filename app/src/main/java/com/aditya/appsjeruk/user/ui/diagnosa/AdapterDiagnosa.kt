package com.aditya.appsjeruk.user.ui.diagnosa

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.remote.request.DiagnosaResponse
import com.aditya.appsjeruk.databinding.ListDiagnosaBinding

class AdapterDiagnosa : ListAdapter<DiagnosaResponse, AdapterDiagnosa.ViewHolder>(DIFF_CALLBACK) {

    private val cfPakar1 = 1.0
    private val cfPakar2 = 0.8
    private val cfPakar3 = 1.0
    private val cfPakar4 = 0.8
    private val cfPakar5 = 0.8
    private val cfPakar6 = 1.0
    private val cfPakar7 = 0.8
    private val cfPakar8 = 0.8
    private val cfPakar9 = 0.6
    private val cfPakar10 = 0.8
    private val cfPakar11 = 0.8
    private val cfPakar12 = 0.8
    var hasilPerhitunganCf: Double = 0.0
        private set

    fun diagnosaPenyakit(): String {
        val selectedSymptoms = currentList.filter { it.isSelected }

        selectedSymptoms.forEach {
            Log.d("Diagnosa", "Gejala terpilih: ${it.namaGejala}")
        }

        //P1
        val isG1Selected = selectedSymptoms.any { it.kodeGejala == "G1" }
        val isG2Selected = selectedSymptoms.any { it.kodeGejala == "G2" }
        val isG3Selected = selectedSymptoms.any { it.kodeGejala == "G3" }
        //P2
        val isG4Selected = selectedSymptoms.any { it.kodeGejala == "G4" }
        val isG5Selected = selectedSymptoms.any { it.kodeGejala == "G5" }
        //P3
        val isG6Selected = selectedSymptoms.any { it.kodeGejala == "G6" }
        val isG7Selected = selectedSymptoms.any { it.kodeGejala == "G7" }
        val isG8Selected = selectedSymptoms.any { it.kodeGejala == "G8" }
        //P4
        val isG9Selected = selectedSymptoms.any { it.kodeGejala == "G9" }
        val isG10Selected = selectedSymptoms.any { it.kodeGejala == "G10" }
        val isG11Selected = selectedSymptoms.any { it.kodeGejala == "G11" }
        val isG12Selected = selectedSymptoms.any { it.kodeGejala == "G12" }
        //P5
        val isG13Selected = selectedSymptoms.any { it.kodeGejala == "G13" }
        val isG14Selected = selectedSymptoms.any { it.kodeGejala == "G14" }
        val isG15Selected = selectedSymptoms.any { it.kodeGejala == "G15" }
        val isG16Selected = selectedSymptoms.any { it.kodeGejala == "G16" }
        val isG17Selected = selectedSymptoms.any { it.kodeGejala == "G17" }
        val isG18Selected = selectedSymptoms.any { it.kodeGejala == "G18" }
        //P6
        val isG19Selected = selectedSymptoms.any { it.kodeGejala == "G19" }
        val isG20Selected = selectedSymptoms.any { it.kodeGejala == "G20" }
        val isG21Selected = selectedSymptoms.any { it.kodeGejala == "G21" }
        val isG22Selected = selectedSymptoms.any { it.kodeGejala == "G22" }
        val isG23Selected = selectedSymptoms.any { it.kodeGejala == "G23" }


        if (isG1Selected && isG2Selected && isG3Selected) {
            val ruleCertainty1 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G1")),
                calculateRuleCertainty(selectedSymptoms, listOf("G2"))
            ) * cfPakar1
            val ruleCertainty2 = calculateRuleCertainty(selectedSymptoms, listOf("G3")) * cfPakar2

            val finalCertainty = (ruleCertainty1 + ruleCertainty2) / 2

            this.hasilPerhitunganCf = finalCertainty
            Log.d("Diagnosa", "Hasil CF P1: ${this.hasilPerhitunganCf}")
            return if (finalCertainty > 0.0) {
                "Embun Jelaga (Capnodium citri)"
            } else {
                "Tidak Diketahui"
            }
        }
        else if (isG4Selected && isG5Selected) {
            val ruleCertainty3 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G4")),
                calculateRuleCertainty(selectedSymptoms, listOf("G5"))
            ) * cfPakar3
            this.hasilPerhitunganCf = ruleCertainty3
            Log.d("Diagnosa", "Hasil CF P1: ${this.hasilPerhitunganCf}")
            return if (ruleCertainty3 > 0.0) {
                "Penyakit Busuk Buah (Phytophthora spp)"
            } else {
                "Tidak Diketahui"
            }
        } else if (isG6Selected && isG7Selected && isG8Selected) {
            val ruleCertainty4 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G6")),
                calculateRuleCertainty(selectedSymptoms, listOf("G7"))
            ) * cfPakar4
            val ruleCertainty5 = calculateRuleCertainty(selectedSymptoms, listOf("G8")) * cfPakar5
            val finalCertainty = (ruleCertainty4 + ruleCertainty5) / 2
            this.hasilPerhitunganCf = finalCertainty

            return if (finalCertainty > 0.0) {
                "Penyakit Busuk Pangkal Batang (Phytophthora spp)"
            } else {
                "Tidak Diketahui"
            }
        } else if (isG9Selected && isG10Selected && isG11Selected && isG12Selected) {
            val ruleCertainty6 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G9")),
                calculateRuleCertainty(selectedSymptoms, listOf("G10")),
                calculateRuleCertainty(selectedSymptoms, listOf("G11")),
                calculateRuleCertainty(selectedSymptoms, listOf("G12"))
            ) * cfPakar6
            val ruleCertainty7 = calculateRuleCertainty(selectedSymptoms, listOf("G13")) * cfPakar7
            val finalCertainty = (ruleCertainty6 + ruleCertainty7) / 2
            this.hasilPerhitunganCf = finalCertainty

            return if (finalCertainty > 0.0) {
                "Penyakit kanker oleh bakteri Xanthomonas campestris cv citri"
            } else {
                "Tidak Diketahui"
            }
        } else if (isG13Selected && isG14Selected && isG15Selected && isG16Selected && isG17Selected && isG18Selected) {

            val ruleCertainty8 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G13")),
                calculateRuleCertainty(selectedSymptoms, listOf("G14")),
                calculateRuleCertainty(selectedSymptoms, listOf("G15"))
            ) * cfPakar8
            val ruleCertainty9 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G16")),
                calculateRuleCertainty(selectedSymptoms, listOf("G17"))
            ) * cfPakar9
            val ruleCertainty10 =
                calculateRuleCertainty(selectedSymptoms, listOf("G18")) * cfPakar10
            val finalCertainty = (ruleCertainty8 + ruleCertainty9 + ruleCertainty10) / 3
            this.hasilPerhitunganCf = finalCertainty

            return if (finalCertainty > 0.0) {
                "Penyakit kudis oleh jamur Sphaceloma fawcetti"
            } else {
                "Tidak Diketahui"
            }
        } else if (isG19Selected && isG20Selected && isG21Selected && isG22Selected && isG23Selected) {
            val ruleCertainty11 = minOf(
                calculateRuleCertainty(selectedSymptoms, listOf("G19")),
                calculateRuleCertainty(selectedSymptoms, listOf("G22")),
                calculateRuleCertainty(selectedSymptoms, listOf("G23"))
            ) * cfPakar11
            val ruleCertainty12 =
                calculateRuleCertainty(selectedSymptoms, listOf("G20", "G21")) * cfPakar12
            val finalCertainty = (ruleCertainty11 + ruleCertainty12) / 2
            this.hasilPerhitunganCf = finalCertainty

            return if (finalCertainty > 0.0) {
                "Busuk diplodia (Botryodiplodia theobromae Pat)"
            } else {
                "Tidak Diketahui"
            }
        } else {
            return "Tidak Diketahui"
        }
    }


    fun getSelectedSymptoms(): List<DiagnosaResponse> {
        return currentList.filter { it.isSelected }
    }

    fun getSelectedTingkatKepastian(): List<DiagnosaResponse> {
        return currentList.filter { it.isSelected }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListDiagnosaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun calculateRuleCertainty(
        selectedSymptoms: List<DiagnosaResponse>,
        gejalaCodes: List<String>
    ): Double {
        val relevantSymptoms = selectedSymptoms.filter { it.kodeGejala in gejalaCodes }
        return if (relevantSymptoms.isNotEmpty()) {
            val averageTingkatKepastian =
                relevantSymptoms.map { it.selectedTingkatKepastian }.average()
            val certaintyWeight = getCertaintyWeight(averageTingkatKepastian)
            averageTingkatKepastian * certaintyWeight
        } else {
            0.0
        }
    }

    private fun getCertaintyWeight(selectedTingkatKepastian: Double): Double {
        return when {
            selectedTingkatKepastian >= -0.8 && selectedTingkatKepastian <= 0.2 -> 1.0
            selectedTingkatKepastian in 0.4..1.0 -> 1.0
            else -> 0.0
        }
    }


    inner class ViewHolder(private val binding: ListDiagnosaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DiagnosaResponse) {
            binding.apply {
                cbFw.setOnCheckedChangeListener(null)
                cbFw.isChecked = data.isSelected
                ilSpineer.visibility = if (data.isSelected) View.VISIBLE else View.GONE
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
                tvTitle.text = data.pertanyaan
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
                        "Tidak Tahu" -> 0.2
                        "Mungkin Iya" -> 0.4
                        "Kemungkinan Besar Iya" -> 0.6
                        "Hampir Pasti Iya" -> 0.8
                        "Pasti Iya" -> 1.0
                        else -> 0.0
                    }

                    Log.d(
                        "SelectedTingkatKepastian",
                        "Selected Tingkat Kepastian: ${data.selectedTingkatKepastian}"
                    )
                }

            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DiagnosaResponse> =
            object : DiffUtil.ItemCallback<DiagnosaResponse>() {
                override fun areItemsTheSame(
                    oldItem: DiagnosaResponse,
                    newItem: DiagnosaResponse
                ): Boolean {
                    return oldItem.idGejala == newItem.idGejala
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: DiagnosaResponse,
                    newItem: DiagnosaResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}


