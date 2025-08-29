package com.example.symptomtracker.presentation

import com.example.symptomtracker.data.InMemorySymptomRepository
import com.example.symptomtracker.domain.model.Symptom
import com.example.symptomtracker.domain.usecase.SaveSymptomUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SymptomEntryViewModel(
  private val saveSymptomUseCase: SaveSymptomUseCase,
  private val symptomRepository: InMemorySymptomRepository
) {
  private val coroutineScope = CoroutineScope(Dispatchers.Default)
  private val _uiState = MutableStateFlow(SymptomEntryState())
  val uiState: StateFlow<SymptomEntryState> = _uiState

  val savedSymptoms: StateFlow<List<Symptom>> = symptomRepository.symptoms

  fun onNameChange(name: String) {
    _uiState.value = _uiState.value.copy(name = name)
  }

  fun onSeverityChange(severity: Float) {
    _uiState.value = _uiState.value.copy(severity = severity)
  }

  fun onNotesChange(notes: String) {
    _uiState.value = _uiState.value.copy(notes = notes)
  }

  fun saveSymptom(onComplete: () -> Unit = {}) {
    val currentState = _uiState.value
    val symptom = Symptom(
      id = Random.nextInt(10000).toString(),
      name = currentState.name,
      severity = currentState.severity.toInt(),
      notes = currentState.notes.takeIf { it.isNotBlank() }
    )
    coroutineScope.launch {
      saveSymptomUseCase(symptom)

      withContext(Dispatchers.Main) {
        onComplete()
      }
    }
  }
}
