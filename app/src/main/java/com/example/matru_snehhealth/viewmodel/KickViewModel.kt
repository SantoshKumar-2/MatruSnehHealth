package com.example.matru_snehhealth.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.matru_snehhealth.database.AppDatabase
import com.example.matru_snehhealth.models.KickLog
import com.example.matru_snehhealth.repository.KickLogRepository
import kotlinx.coroutines.launch

class KickViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: KickLogRepository
    val allKicks: LiveData<List<KickLog>>
    val todayKickCount: LiveData<Int>

    private var lastKickTime: Long = 0

    init {
        val dao = AppDatabase.getDatabase(application).kickLogDao()
        repository = KickLogRepository(dao)
        allKicks = repository.allKicks
        todayKickCount = repository.getTodayKickCount()
    }

    fun addKick() {
        val currentTime = System.currentTimeMillis()
        // Debounce logic: ignore taps within 2 seconds
        if (currentTime - lastKickTime > 2000) {
            lastKickTime = currentTime
            viewModelScope.launch {
                repository.insert(KickLog(timestamp = currentTime))
            }
        }
    }
}
