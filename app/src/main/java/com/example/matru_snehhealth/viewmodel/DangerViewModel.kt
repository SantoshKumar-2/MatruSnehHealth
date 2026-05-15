package com.example.matru_snehhealth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.matru_snehhealth.database.AppDatabase
import com.example.matru_snehhealth.models.DangerAlert
import com.example.matru_snehhealth.repository.DangerAlertRepository
import kotlinx.coroutines.launch

class DangerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DangerAlertRepository

    init {
        val dao = AppDatabase.getDatabase(application).dangerAlertDao()
        repository = DangerAlertRepository(dao)
    }

    fun logDangerSign(symptom: String) {
        viewModelScope.launch {
            repository.insert(DangerAlert(symptom = symptom, timestamp = System.currentTimeMillis()))
        }
    }
}
