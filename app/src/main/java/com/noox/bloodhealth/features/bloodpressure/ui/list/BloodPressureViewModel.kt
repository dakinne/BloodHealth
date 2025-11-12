package com.noox.bloodhealth.features.bloodpressure.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.bloodhealth.features.bloodpressure.domain.usecase.GetAllBloodPressures
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BloodPressureViewModel(
    private val getAllBloodPressures: GetAllBloodPressures
): ViewModel() {

    private val _state = MutableStateFlow(BloodPressureState())
    val state: StateFlow<BloodPressureState> = _state
        .onStart {
            loadData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = _state.value
        )

    private fun loadData() {
        viewModelScope.launch {
            getAllBloodPressures().collect { bloodPressures ->
                _state.update {
                    it.copy(bloodPressures = bloodPressures)
                }
            }
        }
    }
}