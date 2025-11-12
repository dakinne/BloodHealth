package com.noox.bloodhealth.features.bloodpressure.domain.usecase

import com.noox.bloodhealth.features.bloodpressure.data.BloodPressureRepository
import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

class GetAllBloodPressures(
    private val repository: BloodPressureRepository
) {
    operator fun invoke(): Flow<List<BloodPressure>> = repository.getAll()
}