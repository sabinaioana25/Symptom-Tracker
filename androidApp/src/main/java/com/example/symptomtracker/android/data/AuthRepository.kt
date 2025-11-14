package com.example.symptomtracker.android.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class AuthRepository {

  private val firebaseAuth: FirebaseAuth = Firebase.auth

  suspend fun signInAnonymously() {
    if (firebaseAuth.currentUser == null) {
      try {
        firebaseAuth.signInAnonymously().await()
        println("auth success, signed in anonymously")
      } catch (e: Exception) {
        println("authentication failed: ${e.message}")
      }
    } else {
      println("auth skipped, already signed in")
    }
  }
}
