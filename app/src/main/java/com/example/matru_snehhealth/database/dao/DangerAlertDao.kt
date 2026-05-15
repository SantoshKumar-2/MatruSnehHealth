package com.example.matru_snehhealth.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.matru_snehhealth.models.DangerAlert

@Dao
interface DangerAlertDao {
    @Insert
    suspend fun insert(dangerAlert: DangerAlert)
}
