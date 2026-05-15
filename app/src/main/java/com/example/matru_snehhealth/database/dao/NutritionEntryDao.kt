package com.example.matru_snehhealth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.matru_snehhealth.models.NutritionEntry

@Dao
interface NutritionEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<NutritionEntry>)

    @Update
    suspend fun update(entry: NutritionEntry)

    @Query("SELECT * FROM nutrition_entries WHERE date = :date")
    fun getEntriesForDate(date: String): LiveData<List<NutritionEntry>>

    @Query("DELETE FROM nutrition_entries WHERE date < :date")
    suspend fun deleteOldEntries(date: String)

    @Query("SELECT COUNT(*) FROM nutrition_entries WHERE date = :date")
    suspend fun getCountForDate(date: String): Int
}
