package com.example.symptomtracker.domain.usecase

import com.example.symptomtracker.domain.model.Symptom
import com.example.symptomtracker.domain.repository.SymptomRepository

class SaveSymptomUseCase(private val repository: SymptomRepository) {
  suspend operator fun invoke(symptom: Symptom) {
    repository.saveSymptom(symptom)
  }
}
