package com.noox.bloodhealth.features.bloodpressure.ui.list

import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure

sealed class  BloodPressureAction {
    object HideCreateNewPressure: BloodPressureAction()
    object ShowCreateNewPressure: BloodPressureAction()
    data class AddNewPressure(val bloodPressure: BloodPressure): BloodPressureAction()
}