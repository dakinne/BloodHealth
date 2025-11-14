package com.noox.bloodhealth.features.bloodpressure.ui.list

import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure

data class BloodPressureState(
    val bloodPressures: List<BloodPressure> = emptyList(),
    val errorMessage: String? = null,
    val showCreateForm: Boolean = false
)