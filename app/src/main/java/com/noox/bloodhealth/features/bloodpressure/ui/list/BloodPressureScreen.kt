package com.noox.bloodhealth.features.bloodpressure.ui.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.noox.bloodhealth.core.ui.components.Header
import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure
import com.noox.bloodhealth.features.bloodpressure.ui.navigation.Route

@Composable
fun BloodPressureScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    state: BloodPressureState
) {

    Scaffold(
        topBar = { Header() },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
                shape = RoundedCornerShape(24),
                onClick = { navController.navigate(Route.CreateBloodPressureScreen) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) {
        LazyColumn(
            modifier = modifier.padding(it)
        ) {
            items(state.bloodPressures) { bloodPressure ->
                BloodPressureItem(bloodPressure)
            }
        }
    }
}

@Composable
fun BloodPressureItem(bloodPressure: BloodPressure) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier
                .padding(8.dp)
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "${bloodPressure.systolic}/${bloodPressure.diastolic}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${bloodPressure.pulse}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
