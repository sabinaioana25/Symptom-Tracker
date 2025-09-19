package com.example.symptomtracker.domain.repository

import com.example.symptomtracker.domain.model.Note

interface NotesRepository {
    suspend fun insert(note: Note)
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteSingleNote(note: Note)
    suspend fun clear()
}
