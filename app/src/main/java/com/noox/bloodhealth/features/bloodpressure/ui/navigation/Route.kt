package com.noox.bloodhealth.features.bloodpressure.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object BloodPressureScreen: Route
    @Serializable data object CreateBloodPressureScreen: Route
}