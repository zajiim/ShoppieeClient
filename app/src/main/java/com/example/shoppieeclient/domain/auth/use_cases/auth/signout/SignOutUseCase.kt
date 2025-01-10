package com.example.shoppieeclient.domain.auth.use_cases.auth.signout

import com.example.shoppieeclient.domain.auth.models.auth.signout.SignOutModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(private val repository: ShoppieeHomeRepo) {
    operator fun invoke(): Flow<Resource<SignOutModel>> {
        return repository.signOut()
    }
}