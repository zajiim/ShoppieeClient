package com.example.shoppieeclient.data.auth.repository

import com.example.shoppieeclient.data.auth.remote.api.ShoppieApiService
import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpRequestDto
import com.example.shoppieeclient.data.auth.remote.mapper.toUserModel
import com.example.shoppieeclient.domain.auth.models.signup.UserModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.utils.Resource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import java.io.IOException

class ShoppieeRepoImpl(
    private val api: ShoppieApiService
): ShoppieRepo {
    override fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<UserModel>> = flow {
        try {
            emit(Resource.Loading())

            val signUpRequestDto = SignUpRequestDto(
                name,
                email,
                password,
                confirmPassword
            )

            val userResponse = api.signUp(signUpRequestDto)
            if (userResponse.status == 200 && userResponse.result?.data != null) {
                val userModel = userResponse.result.data.toUserModel()
                emit(Resource.Success(userModel))
            } else {
                emit(Resource.Error(userResponse.message))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException){
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
    }
}