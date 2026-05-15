package com.example.matru_snehhealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.matru_snehhealth.activities.BabyGrowthActivity
import com.example.matru_snehhealth.activities.CheckupActivity
import com.example.matru_snehhealth.activities.DangerSignsActivity
import com.example.matru_snehhealth.activities.KickCounterActivity
import com.example.matru_snehhealth.activities.NutritionActivity
import com.example.matru_snehhealth.databinding.ActivityMainBinding
import com.example.matru_snehhealth.workers.NutritionReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        scheduleDailyNutritionReminder()
    }

    private fun setupNavigation() {
        binding.cardKickCounter.setOnClickListener {
            startActivity(Intent(this, KickCounterActivity::class.java))
        }
        binding.cardCheckup.setOnClickListener {
            startActivity(Intent(this, CheckupActivity::class.java))
        }
        binding.cardNutrition.setOnClickListener {
            startActivity(Intent(this, NutritionActivity::class.java))
        }
        binding.cardBabyGrowth.setOnClickListener {
            startActivity(Intent(this, BabyGrowthActivity::class.java))
        }
        binding.cardDangerSigns.setOnClickListener {
            startActivity(Intent(this, DangerSignsActivity::class.java))
        }
    }

    private fun scheduleDailyNutritionReminder() {
        val workRequest = PeriodicWorkRequestBuilder<NutritionReminderWorker>(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "nutrition_daily_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
