package com.example.matru_snehhealth.repository

import androidx.lifecycle.LiveData
import com.example.matru_snehhealth.database.dao.NutritionEntryDao
import com.example.matru_snehhealth.models.NutritionEntry

class NutritionRepository(private val nutritionEntryDao: NutritionEntryDao) {

    fun getEntriesForDate(date: String): LiveData<List<NutritionEntry>> = 
        nutritionEntryDao.getEntriesForDate(date)

    suspend fun insertAll(entries: List<NutritionEntry>) {
        nutritionEntryDao.insertAll(entries)
    }

    suspend fun update(entry: NutritionEntry) {
        nutritionEntryDao.update(entry)
    }

    suspend fun deleteOldEntries(today: String) {
        nutritionEntryDao.deleteOldEntries(today)
    }

    suspend fun getCountForDate(date: String): Int {
        return nutritionEntryDao.getCountForDate(date)
    }
}
