package com.example.matru_snehhealth.repository

import androidx.lifecycle.LiveData
import com.example.matru_snehhealth.database.dao.AppointmentDao
import com.example.matru_snehhealth.models.Appointment

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    val nextAppointment: LiveData<Appointment?> = appointmentDao.getNextAppointment()

    suspend fun insert(appointment: Appointment) {
        appointmentDao.insert(appointment)
    }
}
