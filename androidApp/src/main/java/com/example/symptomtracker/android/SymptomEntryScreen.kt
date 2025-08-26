package com.example.symptomtracker.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.symptomtracker.presentation.SymptomEntryState
import com.example.symptomtracker.presentation.SymptomEntryViewModel

@Composable
fun SymptomEntryScreen(
  viewModel: SymptomEntryViewModel,
  uiState: SymptomEntryState,
  onSaved: () -> Unit,
) {
  Column(modifier = Modifier.padding(16.dp)) {
    OutlinedTextField(
      value = uiState.name,
      onValueChange = viewModel::onNameChange,
      label = { Text("Symptom") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(16.dp))

    Text("Severity: ${uiState.severity}")

    Slider(
      value = uiState.severity.toFloat(),
      onValueChange = viewModel::onSeverityChange,
      valueRange = 1f..10f,
      steps = 8,
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
      value = uiState.notes,
      onValueChange = viewModel::onNotesChange,
      label = { Text("Notes (optional)") },
      modifier = Modifier
        .fillMaxWidth()
    )

    Spacer(Modifier.height(24.dp))

    Button(
      onClick = { viewModel.saveSymptom(onSaved) },
      modifier = Modifier.fillMaxWidth(),
      enabled = uiState.name.isNotBlank()
    ) {
      Text("Save Symptom")
    }

    LazyColumn {  }
  }
}
