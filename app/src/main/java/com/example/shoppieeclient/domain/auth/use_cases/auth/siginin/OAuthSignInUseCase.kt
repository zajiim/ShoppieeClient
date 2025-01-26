package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow


class OAuthSignInUseCase(private val repository: ShoppieRepo) {
    operator fun invoke(
        provider: String,
        token: String
    ): Flow<Resource<SignInUserModel>> {
        return repository.oAuthSignIn(provider, token)
    }
}