package com.example.shoppieeclient.domain.auth.repository

import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel
import com.example.shoppieeclient.domain.auth.models.auth.signout.SignOutModel
import com.example.shoppieeclient.domain.auth.models.auth.signup.SignUpUserModel
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

    fun oAuthSignIn(
        provider: String,
        token: String
    ): Flow<Resource<SignInUserModel>>
}