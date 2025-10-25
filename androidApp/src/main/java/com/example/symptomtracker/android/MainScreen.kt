package com.example.symptomtracker.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import com.example.symptomtracker.presentation.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: NoteViewModel) {
  Scaffold(
    topBar = {
      TopAppBar(title = { Text("Symptom Tracker") })
    },
  ) { innerPadding ->
    NoteScreen(
      viewModel = viewModel,
      modifier = Modifier.padding(innerPadding)
    )
  }
}
