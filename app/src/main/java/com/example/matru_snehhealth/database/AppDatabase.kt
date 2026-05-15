package com.example.matru_snehhealth.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matru_snehhealth.database.dao.AppointmentDao
import com.example.matru_snehhealth.database.dao.DangerAlertDao
import com.example.matru_snehhealth.database.dao.KickLogDao
import com.example.matru_snehhealth.database.dao.NutritionEntryDao
import com.example.matru_snehhealth.models.Appointment
import com.example.matru_snehhealth.models.DangerAlert
import com.example.matru_snehhealth.models.KickLog
import com.example.matru_snehhealth.models.NutritionEntry

@Database(
    entities = [KickLog::class, Appointment::class, NutritionEntry::class, DangerAlert::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kickLogDao(): KickLogDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun nutritionEntryDao(): NutritionEntryDao
    abstract fun dangerAlertDao(): DangerAlertDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "matru_sneh_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
