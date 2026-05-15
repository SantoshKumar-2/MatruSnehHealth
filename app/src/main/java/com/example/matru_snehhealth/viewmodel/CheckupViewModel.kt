package com.example.matru_snehhealth.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import com.example.matru_snehhealth.database.AppDatabase
import com.example.matru_snehhealth.models.Appointment
import com.example.matru_snehhealth.repository.AppointmentRepository
import com.example.matru_snehhealth.workers.CheckupReminderWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CheckupViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppointmentRepository
    val nextAppointment: LiveData<Appointment?>

    init {
        val dao = AppDatabase.getDatabase(application).appointmentDao()
        repository = AppointmentRepository(dao)
        nextAppointment = repository.nextAppointment
    }

    fun scheduleAppointment(timestamp: Long) {
        viewModelScope.launch {
            repository.insert(Appointment(appointmentDate = timestamp))
            scheduleNotification(timestamp)
        }
    }

    private fun scheduleNotification(timestamp: Long) {
        val currentTime = System.currentTimeMillis()
        val delay = (timestamp - TimeUnit.DAYS.toMillis(1)) - currentTime

        if (delay > 0) {
            val workRequest = OneTimeWorkRequestBuilder<CheckupReminderWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(getApplication())
                .enqueueUniqueWork("checkup_reminder", ExistingWorkPolicy.REPLACE, workRequest)
        }
    }
}
