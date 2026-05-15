package com.example.matru_snehhealth.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.matru_snehhealth.R
import com.example.matru_snehhealth.database.AppDatabase
import com.example.matru_snehhealth.models.NutritionEntry
import com.example.matru_snehhealth.repository.NutritionRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NutritionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NutritionRepository
    val todayEntries: LiveData<List<NutritionEntry>>

    init {
        val dao = AppDatabase.getDatabase(application).nutritionEntryDao()
        repository = NutritionRepository(dao)
        val today = getTodayDate()
        todayEntries = repository.getEntriesForDate(today)

        viewModelScope.launch {
            repository.deleteOldEntries(today)
            seedIfNeeded(today)
        }
    }

    private fun getTodayDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    private suspend fun seedIfNeeded(today: String) {
        val count = repository.getCountForDate(today)
        if (count == 0) {
            val foods = listOf(
                getApplication<Application>().getString(R.string.ragi),
                getApplication<Application>().getString(R.string.greens),
                getApplication<Application>().getString(R.string.pulses),
                getApplication<Application>().getString(R.string.milk),
                getApplication<Application>().getString(R.string.fruits)
            )
            val entries = foods.map { NutritionEntry(foodName = it, completed = false, date = today) }
            repository.insertAll(entries)
        }
    }

    fun toggleCompletion(entry: NutritionEntry) {
        viewModelScope.launch {
            repository.update(entry.copy(completed = !entry.completed))
        }
    }
}
