package com.noox.bloodhealth.features.bloodpressure.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.noox.bloodhealth.core.ui.components.Header
import com.noox.bloodhealth.features.bloodpressure.domain.model.BloodPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.AddNewPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.HideCreateNewPressure
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureAction.ShowCreateNewPressure

@Composable
fun BloodPressureScreen(
    modifier: Modifier = Modifier,
    state: BloodPressureState,
    onAction: (BloodPressureAction) -> Unit = {}
) {
    Scaffold(
        topBar = { Header() },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
                shape = RoundedCornerShape(24),
                onClick = { onAction(ShowCreateNewPressure) },
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

    CreateBloodPressureFormDialog(
        show = state.showCreateForm,
        onDismiss = { onAction(HideCreateNewPressure) },
        onAccept = {
            onAction(HideCreateNewPressure)
            onAction(AddNewPressure(it))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBloodPressureFormDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onAccept: (BloodPressure) -> Unit
) {
    if (show) {

        var systonic by remember { mutableIntStateOf(120) }
        var diastolic by remember { mutableIntStateOf(80) }
        var pulse by remember { mutableIntStateOf(60) }

        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {

                    Text(
                        text = "New Blood Pressure",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )

                    InputForm(
                        text = "Systolic",
                        value = systonic,
                        onValueChange = { systonic = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InputForm(
                        text = "Diastolic",
                        value = diastolic,
                        onValueChange = { diastolic = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InputForm(
                        text = "Pulse",
                        value = pulse,
                        onValueChange = { pulse = it }
                    )

                    Button(
                        onClick = {
                            onAccept(
                                BloodPressure(
                                    diastolic = diastolic.toInt(),
                                    systolic = systonic.toInt(),
                                    pulse = pulse.toInt()
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}

@Composable
fun InputForm(
    text: String,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f).padding(start = 16.dp, end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { onValueChange(value - 1) }
                ) {
                    Icon(
                        Icons.Filled.Remove,
                        contentDescription = "remove"
                    )
                }

                Text(
                    modifier = Modifier.width(56.dp),
                    text = "$value",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                IconButton(
                    onClick = { onValueChange(value + 1) }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "plus"
                    )
                }
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
