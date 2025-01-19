package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import android.content.Context
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.example.shoppieeclient.domain.auth.repository.SocialMediaSignInRepo
import com.example.shoppieeclient.utils.Resource

class SignInWithGoogleUseCase(private val socialMediaSignInRepo: SocialMediaSignInRepo) {
    suspend operator fun invoke(activityContext: Context): Resource<GoogleAccount> {
        return socialMediaSignInRepo.signInWithGoogle(activityContext)
    }
}