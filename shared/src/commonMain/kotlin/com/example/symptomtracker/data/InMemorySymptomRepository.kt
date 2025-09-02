//package com.example.symptomtracker.data
//
//import com.example.symptomtracker.domain.model.SurgeryFollowUp
//import com.example.symptomtracker.domain.model.Symptom
//import com.example.symptomtracker.domain.repository.SymptomRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//
//class InMemorySymptomRepository : SymptomRepository {
//
////  private val _symptoms = MutableStateFlow<List<Symptom>>(emptyList())
////  val symptoms: StateFlow<List<Symptom>> = _symptoms
////
////  override suspend fun saveSymptom(symptom: Symptom) {
////    _symptoms.update { it + symptom }
////  }
////
////  private val _followUps = MutableStateFlow<List<SurgeryFollowUp>>(emptyList())
////  val followUps: StateFlow<List<SurgeryFollowUp>> = _followUps
////
////  suspend fun saveFollowUp(followUp: SurgeryFollowUp) {
////    _followUps.update { it + followUp }
////  }
////
////  override suspend fun getAllSymptoms(): List<Symptom> {
////    TODO("Not yet implemented")
////  }
//}
