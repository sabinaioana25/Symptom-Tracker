package com.example.symptomtracker.domain.model

data class Symptom(
  val id: String,
  val name: String,
  val severity: Int,
  val notes: String?
)
