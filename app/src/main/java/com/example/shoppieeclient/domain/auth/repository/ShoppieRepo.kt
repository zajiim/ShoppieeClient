package com.example.shoppieeclient.domain.auth.repository

import com.example.shoppieeclient.domain.auth.models.signin.SignInUserModel
import com.example.shoppieeclient.domain.auth.models.signup.SignUpUserModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieRepo {
    fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<SignUpUserModel>>

    fun signIn(
        email: String,
        password: String
    ): Flow<Resource<SignInUserModel>>
}