package com.example.shoppieeclient.data.auth.repository

import android.util.Log
import com.example.shoppieeclient.data.auth.remote.api.ShoppieApiService
import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.SingInRequestDto
import com.example.shoppieeclient.data.auth.remote.dto.auth.signup.SignUpRequestDto
import com.example.shoppieeclient.data.auth.remote.mapper.auth.signin.toSignInUserModel
import com.example.shoppieeclient.data.auth.remote.mapper.auth.signup.toSignUpUserModel
import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel
import com.example.shoppieeclient.domain.auth.models.auth.signup.SignUpUserModel
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.utils.Resource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import java.io.IOException

private const val TAG = "ShoppieeRepoImpl"

class ShoppieeRepoImpl(
    private val api: ShoppieApiService
) : ShoppieRepo {
    override fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<SignUpUserModel>> = flow {
        try {
            emit(Resource.Loading())

            val signUpRequestDto = SignUpRequestDto(
                name,
                email,
                password,
                confirmPassword
            )

            val userResponse = api.signUp(signUpRequestDto)
            Log.e(
                TAG,
                "status == ${userResponse.status} message:== ${userResponse.message}, data ==${userResponse.result?.data}",
            )
            if (userResponse.status == 200 && userResponse.result?.data != null) {
                val userModel = userResponse.result.data.toSignUpUserModel()
                Log.e(TAG, "after parsing==: ${userResponse.message}")
                emit(Resource.Success(data = userModel, message = userResponse.message))
            } else {
                Log.e(TAG, "after parsing error==: ${userResponse.message}")
                emit(Resource.Error(userResponse.message))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
    }

    override fun signIn(
        email: String,
        password: String
    ): Flow<Resource<SignInUserModel>> = flow {
        try {
            emit(Resource.Loading())
            val signInRequestDto = SingInRequestDto(
                email = email,
                password = password
            )

            val userResponse = api.signIn(signInRequestDto)
            if (userResponse.status == 200 && userResponse.result?.data != null) {
                val userModel = userResponse.result.data.toSignInUserModel()
                emit(Resource.Success(data = userModel, message = userResponse.message))
            } else {
                emit(Resource.Error(userResponse.message))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
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