package com.example.symptomtracker.domain.usecase

import com.example.symptomtracker.domain.model.Note
import com.example.symptomtracker.domain.repository.NoteRepositoryImpl

class NoteUseCase(private val repositoryImpl: NoteRepositoryImpl) {
  suspend operator fun invoke(note: Note) = repositoryImpl.insert(note)
}
