package com.example.symptomtracker.android.presentation.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException


// used to sign in, sign out and to get user info
class GoogleAuthUIClient(
  private val context: Context,
  private val oneTapClient: SignInClient
) {
  private val auth = Firebase.auth
  suspend fun signIn(): IntentSender? {
    val result = try {
      oneTapClient.beginSignIn(
        buildSignInRequest()
      ).await()
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
      null
    }
    return result?.pendingIntent?.intentSender
  }

  suspend fun signInWithIntent(intent: Intent): SignInResult {
    val credential = oneTapClient.getSignInCredentialFromIntent(intent)
    val googleIdToken = credential.googleIdToken
    val googleIdCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

    return try {
      val user = auth.signInWithCredential(googleIdCredentials).await().user
      SignInResult(
        data = user?.run {
          UserData(
            userId = uid,
            username = displayName
          )
        },
        errorMessage = null
      )
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
      SignInResult(
        null,
        errorMessage = e.message
      )
    }
  }

  suspend fun signOut() {
    try {
      oneTapClient.signOut().await()
      auth.signOut()
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
    }
  }

  fun getSignedInUser(): UserData? = auth.currentUser?.run {
    UserData(
      userId = uid,
      username = displayName
    )
  }

  private fun buildSignInRequest(): BeginSignInRequest {
    val resId = context.resources.getIdentifier(
      "default_web_client_id",
      "string",
      context.packageName
    )

    val serverClientId = if (resId != 0) {
      context.getString(resId)
    } else {
      throw Exception("String resource not found")
    }

    return BeginSignInRequest.Builder()
      .setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.Builder()
          .setSupported(true)
          .setFilterByAuthorizedAccounts(true)
          .setServerClientId(serverClientId)
          .build()
      )
      .setAutoSelectEnabled(false)
      .build()
  }
}
