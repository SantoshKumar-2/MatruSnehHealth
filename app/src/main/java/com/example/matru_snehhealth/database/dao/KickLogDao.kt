package com.example.matru_snehhealth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.matru_snehhealth.models.KickLog

@Dao
interface KickLogDao {
    @Insert
    suspend fun insert(kickLog: KickLog)

    @Query("SELECT * FROM kick_logs ORDER BY timestamp DESC")
    fun getAllKicks(): LiveData<List<KickLog>>

    @Query("SELECT COUNT(*) FROM kick_logs WHERE timestamp >= :startOfDay")
    fun getTodayCount(startOfDay: Long): LiveData<Int>
}
