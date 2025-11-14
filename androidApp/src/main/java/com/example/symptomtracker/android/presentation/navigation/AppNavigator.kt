package com.example.symptomtracker.android.presentation.navigation

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.symptomtracker.android.NoteScreen
import com.example.symptomtracker.android.presentation.profile.ProfileScreen
import com.example.symptomtracker.android.presentation.sign_in.GoogleAuthUIClient
import com.example.symptomtracker.android.presentation.sign_in.SignInScreen
import com.example.symptomtracker.android.presentation.sign_in.SignInViewModel
import com.example.symptomtracker.presentation.NoteViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigator(
  googleAuthUiClient: GoogleAuthUIClient,
  noteViewModel: NoteViewModel
) {
  val navController = rememberNavController()
  val context = LocalContext.current
  val activity = context as? androidx.activity.ComponentActivity

  val startDestination = if (googleAuthUiClient.getSignedInUser() != null) {
    Route.NOTE
  } else {
    Route.SIGN_IN
  }

  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    composable(Route.SIGN_IN) {
      val viewModel = viewModel<SignInViewModel>()
      val state by viewModel.state.collectAsStateWithLifecycle()

      val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
          if (result.resultCode == RESULT_OK) {
            viewModel.viewModelScope.launch {
              val signInResult = googleAuthUiClient.signInWithIntent(
                intent = result.data ?: return@launch
              )
              viewModel.onSignInResult(signInResult)
            }
          }
        }
      )

      LaunchedEffect(state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
          Toast.makeText(
            context,
            "sign in ok",
            Toast.LENGTH_LONG
          ).show()

          navController.navigate(Route.NOTE) {
            popUpTo(Route.SIGN_IN) {
              inclusive = true
            }
          }
          viewModel.resetState()
        }
      }

      SignInScreen(
        state = state,
        onSignInClick = {
          viewModel.startSignIn(googleAuthUiClient, launcher)
        }
      )
    }

    composable(Route.PROFILE) {
      ProfileScreen(
        userData = googleAuthUiClient.getSignedInUser(),
        onSignOut = {
          activity?.lifecycleScope?.launch {
            googleAuthUiClient.signOut()
            Toast.makeText(
              context,
              "Signed out",
              Toast.LENGTH_LONG
            ).show()
            navController.popBackStack()
          }
        },
        onGoToNotes = { navController.navigate(Route.NOTE) }
      )
    }

    composable(Route.NOTE) {
     NoteScreen(
       viewModel = noteViewModel,
       onProfileClick = {
         navController.navigate(Route.PROFILE)
       }
     )
    }
  }
}
