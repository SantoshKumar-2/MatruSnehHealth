package com.example.matru_snehhealth.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matru_snehhealth.adapters.KickAdapter
import com.example.matru_snehhealth.databinding.ActivityKickCounterBinding
import com.example.matru_snehhealth.viewmodel.KickViewModel

class KickCounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKickCounterBinding
    private val viewModel: KickViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKickCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = KickAdapter()
        binding.rvKicks.layoutManager = LinearLayoutManager(this)
        binding.rvKicks.adapter = adapter

        viewModel.allKicks.observe(this) { kicks ->
            adapter.submitList(kicks)
        }

        viewModel.todayKickCount.observe(this) { count ->
            binding.tvTodayCount.text = "${getString(com.example.matru_snehhealth.R.string.today_kicks)} $count"
        }

        binding.btnLogKick.setOnClickListener {
            viewModel.addKick()
        }
    }
}
