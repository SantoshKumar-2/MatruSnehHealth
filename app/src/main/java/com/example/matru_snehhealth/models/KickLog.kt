package com.example.matru_snehhealth.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kick_logs")
data class KickLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long
)
