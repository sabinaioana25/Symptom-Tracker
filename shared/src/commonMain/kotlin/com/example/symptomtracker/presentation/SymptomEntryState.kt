package com.example.symptomtracker.presentation

data class SymptomEntryState(
  val name: String = "",
  val severity: Int = 5,
  val notes: String = "",
)
