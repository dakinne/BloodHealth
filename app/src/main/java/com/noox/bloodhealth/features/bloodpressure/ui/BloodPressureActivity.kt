package com.noox.bloodhealth.features.bloodpressure.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.noox.bloodhealth.core.ui.theme.BloodHealthTheme
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureScreen
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureViewModel
import org.koin.androidx.compose.koinViewModel

class BloodPressureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloodHealthTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<BloodPressureViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    BloodPressureScreen(
                        state = state,
                        onAction = { viewModel.onAction(it) }
                    )
                }
            }
        }
    }
}
