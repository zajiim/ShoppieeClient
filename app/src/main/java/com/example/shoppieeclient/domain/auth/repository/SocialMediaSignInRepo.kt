package com.example.shoppieeclient.domain.auth.repository

import android.content.Context
import com.example.shoppieeclient.domain.auth.models.auth.FacebookAccount
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.example.shoppieeclient.utils.Resource

interface SocialMediaSignInRepo {
    suspend fun signInWithGoogle(activityContext: Context): Resource<GoogleAccount>
    suspend fun signInWithFacebook(activityContext: Context): Resource<FacebookAccount>
}