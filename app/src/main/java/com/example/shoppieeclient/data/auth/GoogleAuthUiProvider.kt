package com.example.shoppieeclient.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.shoppieeclient.BuildConfig
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

private const val TAG = "GoogleAuthUiProvider"
class GoogleAuthUiProvider {
    suspend fun signIn(
        activityContext: Context, credentialManager: CredentialManager
    ): GoogleAccount {
        // 13th video

        val cred = credentialManager.getCredential(
            activityContext, getCredentialRequest()
        ).credential
        return handleCredentials(cred)
    }

    private fun handleCredentials(cred: Credential): GoogleAccount {
        when {
            cred is CustomCredential && cred.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIdTokenCredential = cred as GoogleIdTokenCredential
                Log.e(TAG, "handleCredentials: $googleIdTokenCredential", )
                return GoogleAccount(
                    token = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName ?: "",
                    profileImageUrl = googleIdTokenCredential.profilePictureUri.toString()
                )
            }
            else -> {
                throw IllegalStateException("Invalid credential type")
            }
        }
    }

    private fun getCredentialRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder().addCredentialOption(
                GetSignInWithGoogleOption
                    .Builder(BuildConfig.GOOGLE_CLIENT_ID)
                    .build()
            ).build()
    }
}