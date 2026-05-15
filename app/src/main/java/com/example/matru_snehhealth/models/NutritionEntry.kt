package com.example.matru_snehhealth.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_entries")
data class NutritionEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodName: String,
    val completed: Boolean,
    val date: String // Using String for simplicity in daily filtering (e.g., "yyyy-MM-dd")
)
