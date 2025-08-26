package com.example.symptomtracker.presentation

data class SymptomEntryState(
  val name: String = "",
  val severity: Float = 5f,
  val notes: String = "",
)
