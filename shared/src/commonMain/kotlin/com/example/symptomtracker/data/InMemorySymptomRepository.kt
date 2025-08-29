package com.example.symptomtracker.data

import com.example.symptomtracker.domain.model.Symptom
import com.example.symptomtracker.domain.repository.SymptomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InMemorySymptomRepository : SymptomRepository {

  private val _symptoms = MutableStateFlow<List<Symptom>>(emptyList())
  val symptoms: StateFlow<List<Symptom>> = _symptoms

  override suspend fun saveSymptom(symptom: Symptom) {
    _symptoms.update { currentList ->
      currentList + symptom
    }
  }

  override suspend fun getAllSymptoms(): List<Symptom> {
    TODO("Not yet implemented")
  }

}
