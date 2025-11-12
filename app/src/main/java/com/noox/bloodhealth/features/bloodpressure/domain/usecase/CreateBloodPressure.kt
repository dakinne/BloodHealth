package com.noox.bloodhealth.features.bloodpressure.domain.usecase

import com.noox.bloodhealth.features.bloodpressure.data.BloodPressureRepository
import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure

class CreateBloodPressure(
    private val repository: BloodPressureRepository
) {
    operator fun invoke(bloodPressure: BloodPressure): Unit =
        repository.create(bloodPressure)
}