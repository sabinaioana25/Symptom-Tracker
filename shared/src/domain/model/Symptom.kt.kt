data class `Symptom.kt`(
  val id: String,
  val name: String,
  val severity: Int,
  val startedAt: Long,
  val durationMinutes: Int?,
  val activitiesBefore: String,
  val medicationTaken: Boolean,
  val medicationDetails: String?,
  val moodImpact: Int,
  val additionalNotes: String?
)
