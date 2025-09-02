package com.example.symptomtracker.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.symptomtracker.data.InMemorySymptomRepository
import com.example.symptomtracker.domain.repository.SymptomRepository
import com.example.symptomtracker.domain.usecase.SaveSymptomUseCase
import com.example.symptomtracker.presentation.SymptomEntryViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val repository = FakeSymptomRepository()
    val useCase = SaveSymptomUseCase(repository)
    val symptomRepo = InMemorySymptomRepository()
    val viewModel = SymptomEntryViewModel(
      useCase,
      symptomRepo)

    setContent {
      val state by viewModel.uiState.collectAsState()

      MaterialTheme {
        SymptomEntryScreen(
          viewModel = viewModel, uiState = state
        ) {
          Toast.makeText(
            this, "Symptom saved!", Toast.LENGTH_SHORT
          ).show()
        }
      }
    }
  }
}
