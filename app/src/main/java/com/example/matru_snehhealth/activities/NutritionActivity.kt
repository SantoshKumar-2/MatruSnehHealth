package com.example.matru_snehhealth.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matru_snehhealth.adapters.NutritionAdapter
import com.example.matru_snehhealth.databinding.ActivityNutritionBinding
import com.example.matru_snehhealth.viewmodel.NutritionViewModel

class NutritionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNutritionBinding
    private val viewModel: NutritionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutritionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NutritionAdapter { entry ->
            viewModel.toggleCompletion(entry)
        }

        binding.rvNutrition.layoutManager = LinearLayoutManager(this)
        binding.rvNutrition.adapter = adapter

        viewModel.todayEntries.observe(this) { entries ->
            adapter.submitList(entries)
        }
    }
}
