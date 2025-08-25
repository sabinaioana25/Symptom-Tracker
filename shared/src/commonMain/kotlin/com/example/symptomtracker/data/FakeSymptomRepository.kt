package com.example.symptomtracker.data

import com.example.symptomtracker.domain.model.Symptom
import com.example.symptomtracker.domain.repository.SymptomRepository

class FakeSymptomRepository : SymptomRepository {
  private val recordedSymptomsList = mutableListOf<Symptom>()

  override suspend fun saveSymptom(symptom: Symptom) {
    recordedSymptomsList.add(symptom)
  }

  override suspend fun getAllSymptoms(): List<Symptom> = recordedSymptomsList
}
