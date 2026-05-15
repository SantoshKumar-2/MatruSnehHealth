package com.example.matru_snehhealth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matru_snehhealth.utils.BabyGrowthData

class BabyGrowthViewModel : ViewModel() {
    private val _currentWeek = MutableLiveData(4)
    val currentWeek: LiveData<Int> = _currentWeek

    private val _growthDescription = MutableLiveData<String>()
    val growthDescription: LiveData<String> = _growthDescription

    init {
        updateDescription()
    }

    fun nextWeek() {
        _currentWeek.value?.let {
            if (it < 40) {
                _currentWeek.value = it + 1
                updateDescription()
            }
        }
    }

    fun previousWeek() {
        _currentWeek.value?.let {
            if (it > 4) {
                _currentWeek.value = it - 1
                updateDescription()
            }
        }
    }

    private fun updateDescription() {
        _growthDescription.value = BabyGrowthData.getDescription(_currentWeek.value ?: 4)
    }
}
