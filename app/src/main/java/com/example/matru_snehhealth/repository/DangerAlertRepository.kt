package com.example.matru_snehhealth.repository

import com.example.matru_snehhealth.database.dao.DangerAlertDao
import com.example.matru_snehhealth.models.DangerAlert

class DangerAlertRepository(private val dangerAlertDao: DangerAlertDao) {
    suspend fun insert(dangerAlert: DangerAlert) {
        dangerAlertDao.insert(dangerAlert)
    }
}
