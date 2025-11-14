package com.example.symptomtracker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.symptomtracker.android.data.FirebaseNotesRepository
import com.example.symptomtracker.android.presentation.navigation.AppNavigator
import com.example.symptomtracker.android.presentation.sign_in.GoogleAuthUIClient
import com.example.symptomtracker.presentation.NoteViewModel
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

  private val googleAuthUiClient by lazy {
    GoogleAuthUIClient(
      context = applicationContext,
      oneTapClient = Identity.getSignInClient(applicationContext)
    )
  }
  private val viewModel = NoteViewModel(FirebaseNotesRepository())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
//        MainScreen(viewModel)
        AppNavigator(
          googleAuthUiClient = googleAuthUiClient,
          noteViewModel = viewModel)
      }
    }
  }
}
