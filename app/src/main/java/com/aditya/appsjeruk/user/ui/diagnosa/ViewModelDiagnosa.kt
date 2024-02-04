package com.aditya.appsjeruk.user.ui.diagnosa

import androidx.lifecycle.ViewModel
import com.aditya.appsjeruk.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelDiagnosa @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {


}