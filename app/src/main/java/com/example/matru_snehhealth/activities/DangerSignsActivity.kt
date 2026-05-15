package com.example.matru_snehhealth.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.matru_snehhealth.databinding.ActivityDangerSignsBinding
import com.example.matru_snehhealth.viewmodel.DangerViewModel

class DangerSignsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDangerSignsBinding
    private val viewModel: DangerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangerSignsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        val clickListener = View.OnClickListener { view ->
            val symptom = when (view.id) {
                binding.btnSwelling.id -> binding.btnSwelling.text.toString()
                binding.btnBleeding.id -> binding.btnBleeding.text.toString()
                binding.btnHeadache.id -> binding.btnHeadache.text.toString()
                binding.btnNoMovement.id -> binding.btnNoMovement.text.toString()
                else -> "Unknown"
            }
            viewModel.logDangerSign(symptom)
            binding.cardEmergency.visibility = View.VISIBLE
        }

        binding.btnSwelling.setOnClickListener(clickListener)
        binding.btnBleeding.setOnClickListener(clickListener)
        binding.btnHeadache.setOnClickListener(clickListener)
        binding.btnNoMovement.setOnClickListener(clickListener)

        binding.btnCallEmergency.setOnClickListener {
            // Placeholder for emergency call (e.g., 108 in India)
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:108")
            startActivity(intent)
        }
    }
}
