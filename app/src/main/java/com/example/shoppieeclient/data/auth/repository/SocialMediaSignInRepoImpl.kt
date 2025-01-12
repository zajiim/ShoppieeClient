package com.example.shoppieeclient.data.auth.repository

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.shoppieeclient.BuildConfig
import com.example.shoppieeclient.domain.auth.models.auth.FacebookAccount
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.example.shoppieeclient.domain.auth.repository.SocialMediaSignInRepo
import com.example.shoppieeclient.utils.Resource
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SocialMediaSignInRepoImpl(
    private val credentialManager: CredentialManager,
    private val callbackManager: CallbackManager
) : SocialMediaSignInRepo {
//    private lateinit var callbackManager: CallbackManager

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

    override suspend fun signInWithFacebook(activityContext: Context): Resource<FacebookAccount> {
        return try {
            suspendCancellableCoroutine { continuation ->
//                callbackManager = CallbackManager.Factory.create()

                LoginManager.getInstance().registerCallback(
                    callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onCancel() {
                            continuation.resume(Resource.Error("Facebook login cancelled"))
                        }

                        override fun onError(error: FacebookException) {
                            continuation.resume(
                                Resource.Error(error.localizedMessage ?: "Facebook Login failed")
                            )
                        }

                        override fun onSuccess(result: LoginResult) {

                            val token = result.accessToken.token
                            val profile = Profile.getCurrentProfile()

                            profile?.let {
                                val account = FacebookAccount(
                                    token = token,
                                    displayName = it.name ?: "",
                                    profileImageUrl = it.getProfilePictureUri(200, 200).toString()
                                )
                                continuation.resume(Resource.Success(account))
                            } ?: run {
                                continuation.resume(Resource.Error("Facebook Login failed"))
                            }


//                            val profile = Profile.getCurrentProfile()
//                            val request = GraphRequest.newMeRequest(
//                                result.accessToken
//                            ) { jsonObject, _ ->
//                                val account = FacebookAccount(
//                                    token = result.accessToken.token,
//                                    displayName = jsonObject?.optString("name") ?: "",
////                                    profileImageUrl = jsonObject?.optString("picture") ?: ""
//                                    profileImageUrl = profile?.getProfilePictureUri(200, 200).toString()
//                                )
//                                continuation.resume(Resource.Success(account))
//                            }
//                            request.parameters
                        }

                    }
                )
                LoginManager.getInstance().logInWithReadPermissions(
                    activityContext as ComponentActivity,
                    callbackManager,
                    listOf("public_profile", "email")
                )
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