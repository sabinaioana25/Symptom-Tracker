package com.symptrack.domain.usecase

import com.symptrack.domain.model.Symptom
import com.symptrack.domain.repository.SymptomRepository

class `SaveSymptomUseCase.kt`(private val repository: SymptomRepository) {
  suspend operator fun invoke(symptom: Symptom) {
    repository.saveSymptom(symptom.toDataModel())
  }
}
