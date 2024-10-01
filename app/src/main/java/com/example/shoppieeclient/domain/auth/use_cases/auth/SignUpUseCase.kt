package com.example.shoppieeclient.domain.auth.use_cases.auth

import com.example.shoppieeclient.domain.auth.models.signup.UserModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignUpUseCase (private val repository: ShoppieRepo) {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<UserModel>> {
        return repository.signUp(name, email, password, confirmPassword)
    }
}