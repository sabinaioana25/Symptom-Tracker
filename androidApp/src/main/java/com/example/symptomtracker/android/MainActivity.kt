package com.example.symptomtracker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.symptomtracker.MainScreen
import com.example.symptomtracker.db.AppDatabase
import com.example.symptomtracker.domain.repository.NoteRepositoryImpl
import com.example.symptomtracker.presentation.NoteViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val driver = AndroidSqliteDriver(AppDatabase.Schema, this, "notes.db")
    val db = AppDataBase(driver)
    val repository = NoteRepositoryImpl(db)
    val viewModel = NoteViewModel(repository)

    setContent {
      MaterialTheme {
        MainScreen(viewModel)
      }
    }
  }
}
