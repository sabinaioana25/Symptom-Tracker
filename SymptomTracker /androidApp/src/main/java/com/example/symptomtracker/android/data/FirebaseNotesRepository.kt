package com.example.symptomtracker.android.data

import com.example.symptomtracker.domain.model.Note
import com.example.symptomtracker.domain.repository.NotesRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseNotesRepository : NotesRepository {
  private val db = FirebaseFirestore.getInstance()
  private val notesCollection = db.collection("notes")

  override suspend fun insert(note: Note) {
    notesCollection.document(note.id.toString()).set(note).await()
  }

  override suspend fun getAllNotes(): List<Note> {
    return notesCollection.get().await().documents.mapNotNull {
      it.toObject(Note::class.java)
    }
  }

  override suspend fun deleteSingleNote(note: Note) {
    TODO("Not yet implemented")
  }

  override suspend fun clear() {
    TODO("Not yet implemented")
  }
}
