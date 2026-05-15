package com.example.matru_snehhealth.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "danger_alerts")
data class DangerAlert(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val symptom: String,
    val timestamp: Long
)
