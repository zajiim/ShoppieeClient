package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignInUseCase(private val repository: ShoppieRepo) {
    operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<SignInUserModel>> {
        return repository.signIn(email, password)
    }
}