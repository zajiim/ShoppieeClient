package com.example.shoppieeclient.data.home.account.repository

import android.util.Log
import com.example.shoppieeclient.data.home.account.local.dao.ProfileDao
import com.example.shoppieeclient.data.home.account.remote.api.ShoppieeUserProfileService
import com.example.shoppieeclient.data.home.account.remote.dto.UpdateProfileBody
import com.example.shoppieeclient.data.home.account.remote.mapper.toProfileDataModel
import com.example.shoppieeclient.data.home.account.remote.mapper.toProfileEntity
import com.example.shoppieeclient.data.home.account.remote.mapper.toUpdateProfileModel
import com.example.shoppieeclient.domain.home.account.models.ProfileDataModel
import com.example.shoppieeclient.domain.home.account.models.UpdateProfileModel
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
import com.example.shoppieeclient.utils.Resource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import java.io.IOException

private const val TAG = "ShoppieeUpdateProfileRe"
class ShoppieeUpdateProfileRepoImpl(
    private val shoppieeUserProfileService: ShoppieeUserProfileService,
    private val profileDao: ProfileDao
): ShoppieeUserProfileRepo {
    override fun updateProfile(
        name: String,
        profileImage: String
    ): Flow<Resource<UpdateProfileModel>> = flow{
        try {
            emit(Resource.Loading())
            val updateProfileRequest = UpdateProfileBody(
                name = name,
                profileImage = profileImage
            )
            val updateProfileResponse = shoppieeUserProfileService.updateProfile(updateProfileBody = updateProfileRequest)
            val result = updateProfileResponse.result
            if (updateProfileResponse.status == 200) {
                val updateProfileResult = result.data.toUpdateProfileModel()
                emit(Resource.Success(updateProfileResult))
            } else {
                emit(Resource.Error(updateProfileResponse.message))
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
    }.catch {
        emit(Resource.Error(it.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun getProfileData(): Flow<Resource<ProfileDataModel>> = flow {

        val cachedData = profileDao.getProfile()?.toProfileDataModel()
        cachedData?.let {
            emit(Resource.Success(it))
        }
        try {
            emit(Resource.Loading())
            val getProfileResponse = shoppieeUserProfileService.getProfileData()
            if (getProfileResponse.status == 200) {
//                val result = getProfileResponse.result
//                Log.e(TAG, "getProfileData:>>>>> $result ", )
//                val profileData = result.toProfileDataModel()
//                Log.e(TAG, "profileData:>>>>> $profileData ", )
//                emit(Resource.Success(profileData))
                val profileData = getProfileResponse.result.toProfileDataModel()
                profileDao.insertProfile(profileData.toProfileEntity())
                emit(Resource.Success(profileData))
            } else {
                emit(Resource.Error(getProfileResponse.message))
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
    }.catch {
        emit(Resource.Error(it.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)
}