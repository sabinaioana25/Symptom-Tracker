package com.example.symptomtracker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.symptomtracker.DatabaseDriverFactory
import com.example.symptomtracker.db.AppDatabase
import com.example.symptomtracker.domain.repository.NoteRepositoryImpl
import com.example.symptomtracker.presentation.NoteViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val databaseDriverFactory = DatabaseDriverFactory(this)
    val repository = NoteRepositoryImpl(AppDatabase(databaseDriverFactory.createDriver()))
    val viewModel = NoteViewModel(repository)

    setContent {
      MaterialTheme {
        MainScreen(viewModel)
        // TODO: Change to NavHost
      }
    }
  }
}
