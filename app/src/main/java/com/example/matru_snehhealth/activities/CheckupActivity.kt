package com.example.matru_snehhealth.activities

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.matru_snehhealth.R
import com.example.matru_snehhealth.databinding.ActivityCheckupBinding
import com.example.matru_snehhealth.viewmodel.CheckupViewModel
import java.util.*
import java.util.concurrent.TimeUnit

class CheckupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckupBinding
    private val viewModel: CheckupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPickDate.setOnClickListener {
            showDatePicker()
        }

        viewModel.nextAppointment.observe(this) { appointment ->
            if (appointment != null) {
                val diff = appointment.appointmentDate - System.currentTimeMillis()
                val days = TimeUnit.MILLISECONDS.toDays(diff)
                binding.tvDaysRemaining.text = if (days < 0) "0" else days.toString()
            } else {
                binding.tvDaysRemaining.text = "--"
                binding.tvCountdownLabel.text = getString(R.string.no_appointment)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                viewModel.scheduleAppointment(selectedDate.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}
