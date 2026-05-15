package com.example.matru_snehhealth.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.matru_snehhealth.R
import com.example.matru_snehhealth.databinding.ActivityBabyGrowthBinding
import com.example.matru_snehhealth.viewmodel.BabyGrowthViewModel

class BabyGrowthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBabyGrowthBinding
    private val viewModel: BabyGrowthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBabyGrowthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.currentWeek.observe(this) { week ->
            binding.tvWeekNumber.text = getString(R.string.week_format, week)
        }

        viewModel.growthDescription.observe(this) { description ->
            binding.tvGrowthDescription.text = description
        }

        binding.btnNext.setOnClickListener {
            viewModel.nextWeek()
        }

        binding.btnPrevious.setOnClickListener {
            viewModel.previousWeek()
        }
    }
}
