package com.example.symptomtracker.domain.repository

import com.example.symptomtracker.db.AppDatabase
import com.example.symptomtracker.domain.model.Note

class NoteRepositoryImpl(private val db: AppDatabase) {

  fun insert(note: Note) {
    db.noteQueries.insertNote(
      id = note.id,
      content = note.content)
  }

  fun getAllNotes(): List<Note> {
    return db.noteQueries.getAllNotes()
      .executeAsList()
      .map { Note(it.id, it.content) }
  }

  fun deleteSingleNote(note: Note) {
    db.noteQueries.deleteNote(note.id)
  }

  fun clear() {
    db.noteQueries.deleteAll()
  }
}
