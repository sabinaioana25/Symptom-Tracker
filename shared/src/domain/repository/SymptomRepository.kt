package com.symptrack.domain.repository

import com.symptrack.data.model.SymptomDataModel

interface SymptomRepository {
  suspend fun saveSymptom(symptom: SymptomDataModel)
  suspend fun getAllSymptoms(): List<SymptomDataModel>
}
