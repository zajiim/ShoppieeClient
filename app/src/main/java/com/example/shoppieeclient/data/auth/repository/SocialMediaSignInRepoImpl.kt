package com.example.shoppieeclient.data.auth.repository

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.shoppieeclient.BuildConfig
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.example.shoppieeclient.domain.auth.repository.SocialMediaSignInRepo
import com.example.shoppieeclient.utils.Resource
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class SocialMediaSignInRepoImpl(
    private val credentialManager: CredentialManager
) : SocialMediaSignInRepo {
    override suspend fun signInWithGoogle(activityContext: Context): Resource<GoogleAccount> {
        return try {
            val credential = credentialManager.getCredential(
                activityContext, getGoogleCredentialRequest()
            ).credential

            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleCredential = credential as GoogleIdTokenCredential
                val account = GoogleAccount(
                    token = googleCredential.idToken,
                    displayName = googleCredential.displayName ?: "",
                    profileImageUrl = googleCredential.profilePictureUri.toString()
                )
                Resource.Success(data = account)
            } else {
                Resource.Error("Invalid credential type")
            }


        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    private fun getGoogleCredentialRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder().addCredentialOption(
                GetSignInWithGoogleOption.Builder(
                    BuildConfig.GOOGLE_CLIENT_ID
                ).build()
            ).build()
    }
}