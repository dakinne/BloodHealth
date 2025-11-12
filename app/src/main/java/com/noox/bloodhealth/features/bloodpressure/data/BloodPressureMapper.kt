package com.noox.bloodhealth.features.bloodpressure.data

import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure

fun List<BloodPressureEntity>.toModel(): List<BloodPressure> {
    return map {
        BloodPressure(
            pulse = it.pulse,
            systolic = it.systolic,
            diastolic = it.diastolic
        )
    }
}

fun BloodPressure.toEntity(): BloodPressureEntity {
    return BloodPressureEntity(
        pulse = pulse,
        systolic = systolic,
        diastolic = diastolic
    )
}