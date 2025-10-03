package com.example.symptomtracker.domain.repository

import com.example.symptomtracker.domain.model.Symptom

interface SymptomRepository {
  suspend fun saveSymptom(symptom: Symptom)
  suspend fun getAllSymptoms(): List<Symptom>
}
