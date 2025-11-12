package com.noox.bloodhealth.features.bloodpressure.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.noox.bloodhealth.features.bloodpressure.ui.create.CreateBloodPressureScreen
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureScreen
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.BloodPressureScreen
    ) {
        composable<Route.BloodPressureScreen> {
            val viewModel = koinViewModel<BloodPressureViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BloodPressureScreen(
                navController = navController,
                state = state
            )
        }
        composable<Route.CreateBloodPressureScreen> {
            CreateBloodPressureScreen()
        }
    }
}