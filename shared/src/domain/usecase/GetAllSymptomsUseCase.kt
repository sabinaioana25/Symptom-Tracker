package com.symptrack.domain.usecase

import com.symptrack.domain.model.Symptom
import com.symptrack.domain.repository.SymptomRepository

class GetAllSymptomsUseCase(private val repository: SymptomRepository) {
  suspend operator fun invoke(): List<Symptom> {
    return repository.getAllSymptoms().map { it.toDomainModel() }
  }
}
