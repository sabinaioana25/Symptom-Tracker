package com.example.symptomtracker.presentation.note

import com.example.symptomtracker.domain.model.Note
import com.example.symptomtracker.domain.repository.NoteRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class NoteViewModel(private val repository: NoteRepositoryImpl) {
  private val scope = CoroutineScope(Dispatchers.Default)
  private val _text = MutableStateFlow("")
  val text: StateFlow<String> = _text
  private val _notes = MutableStateFlow<List<Note>>(emptyList())
  val notes: MutableStateFlow<List<Note>> = _notes

  init {
    loadNotes()
  }

  fun onTextChange(newValue: String) {
    _text.value = newValue
  }

  @OptIn(ExperimentalTime::class)
  fun saveNote() {
    val note = Note(
      id = Clock.System.now().toEpochMilliseconds(),
      content = _text.value.trim()
    )

    if (note.content.isNotEmpty()) {
      scope.launch {
        repository.insert(note)
        _text.value = ""
        loadNotes()
      }
    }
  }

  private fun loadNotes() {
    scope.launch {
      val allNotes = repository.getAllNotes()
      _notes.update { allNotes }
    }
  }

  fun deleteNote(note: Note) {
    scope.launch {
      repository.deleteSingleNote(note)
      loadNotes()
    }
  }
}
