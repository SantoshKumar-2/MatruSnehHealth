package com.example.matru_snehhealth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matru_snehhealth.models.Appointment

@Dao
interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appointment: Appointment)

    @Query("SELECT * FROM appointments ORDER BY appointmentDate ASC LIMIT 1")
    fun getNextAppointment(): LiveData<Appointment?>
}
