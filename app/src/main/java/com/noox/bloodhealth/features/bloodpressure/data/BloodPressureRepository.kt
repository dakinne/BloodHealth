package com.noox.bloodhealth.features.bloodpressure.data

import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BloodPressureRepository(
    private val dao: BloodPressureDao
) {
    fun getAll(): Flow<List<BloodPressure>> =
        dao.getAll().map { it.toModel() }

    suspend fun create(bloodPressure: BloodPressure) =
        dao.insert(bloodPressure.toEntity())
}