package com.example.symptomtracker.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.symptomtracker.presentation.note.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
  viewModel: NoteViewModel,
  modifier: Modifier = Modifier
) {
  val input by viewModel.text.collectAsState()
  val notes by viewModel.notes.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {

    TextField(
      value = input,
      onValueChange = viewModel::onTextChange,
      placeholder = { Text("Enter your name") },
      modifier = Modifier.Companion.fillMaxWidth()
    )

    Spacer(modifier = Modifier.Companion.height(8.dp))

    Button(
      onClick = viewModel::saveNote,
      modifier = Modifier.Companion.fillMaxWidth()
    ) {
      Text("Save Note")
    }

    Spacer(modifier = Modifier.Companion.height(8.dp))

    Button(
      onClick = { (viewModel::deleteNote)(notes.last()) },
      modifier = Modifier.Companion.fillMaxWidth()
    ) {
      Text("Delete Note")
    }

    Spacer(modifier = Modifier.Companion.height(8.dp))

    LazyColumn {
      items(notes) { note ->
        Text("• ${note.content}", style = MaterialTheme.typography.bodyMedium)
      }
    }
  }
}
