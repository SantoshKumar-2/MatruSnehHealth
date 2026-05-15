package com.example.matru_snehhealth.repository

import androidx.lifecycle.LiveData
import com.example.matru_snehhealth.database.dao.KickLogDao
import com.example.matru_snehhealth.models.KickLog
import java.util.Calendar

class KickLogRepository(private val kickLogDao: KickLogDao) {

    val allKicks: LiveData<List<KickLog>> = kickLogDao.getAllKicks()

    fun getTodayKickCount(): LiveData<Int> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return kickLogDao.getTodayCount(calendar.timeInMillis)
    }

    suspend fun insert(kickLog: KickLog) {
        kickLogDao.insert(kickLog)
    }
}
