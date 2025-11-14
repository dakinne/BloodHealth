package com.noox.bloodhealth.features.bloodpressure.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure
import com.noox.bloodhealth.features.bloodpressure.domain.usecase.GetAllBloodPressures
import com.noox.bloodhealth.features.bloodpressure.domain.usecase.CreateBloodPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.AddNewPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.HideCreateNewPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.ShowCreateNewPressure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BloodPressureViewModel(
    private val getAllBloodPressures: GetAllBloodPressures,
    private val createBloodPressure: CreateBloodPressure
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

    fun onAction(action: BloodPressureAction) {
        when (action) {
            HideCreateNewPressure -> onHideCreateNewPressure()
            ShowCreateNewPressure -> onShowCreateNewPressure()
            is AddNewPressure -> onCreateBloodPressure(action.bloodPressure)
        }
    }

    private fun onHideCreateNewPressure() {
        _state.update { it.copy(showCreateForm = false) }
    }

    private fun onShowCreateNewPressure() {
        _state.update { it.copy(showCreateForm = true) }
    }

    private fun onCreateBloodPressure(bloodPressure: BloodPressure) {
        viewModelScope.launch {
            createBloodPressure(bloodPressure)
        }
    }
}