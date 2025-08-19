package com.symptrack.domain.usecase

import com.symptrack.data.model.SymptomDataModel
import com.symptrack.domain.repository.SymptomRepository

class `SymptomRepositoryImpl.kt` : SymptomRepository {

  private val symptoms = mutableListOf<SymptomDataModel>()

  override suspend fun saveSymptom(symptom: SymptomDataModel) {
    symptoms.add(symptom)
  }

  override suspend fun getAllSymptoms(): List<SymptomDataModel> {
    return symptoms.toList()
  }
}
