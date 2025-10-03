package com.example.starlingroundup.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starlingroundup.presentation.roundup.RoundupUiState
import com.example.starlingroundup.presentation.roundup.RoundupViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoundupScreen(viewModel: RoundupViewModel = hiltViewModel()) {
  val state by viewModel.uiState.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.loadRoundup()
  }

  when (state) {
    is RoundupUiState.Loading -> CircularProgressIndicator()
    is RoundupUiState.Ready -> {
      val ready = state as RoundupUiState.Ready

      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
      ) {
        Text("This week's roundup: £${"%.2f"}".format(ready.roundup))

        Spacer(modifier = Modifier.height(12.dp))

        ready.goals.forEach { goal ->
          Button(
            onClick = { viewModel.saveToGoal(goal.id, ready.roundup) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
          ) {
            Text("Save to ${goal.name}")
          }
        }

      }
    }
    is RoundupUiState.Saved -> Text("Roundup deposited successfully! 🎉")
    is RoundupUiState.Error -> Text("⚠️ ${(state as RoundupUiState.Error).message}")
  }
}
