package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import android.content.Context
import com.example.shoppieeclient.domain.auth.models.auth.FacebookAccount
import com.example.shoppieeclient.domain.auth.repository.SocialMediaSignInRepo
import com.example.shoppieeclient.utils.Resource

class SignInWithFacebookUseCase(private val repository: SocialMediaSignInRepo) {
    suspend operator fun invoke(activityContext: Context): Resource<FacebookAccount> {
        return repository.signInWithFacebook(activityContext)
    }
}