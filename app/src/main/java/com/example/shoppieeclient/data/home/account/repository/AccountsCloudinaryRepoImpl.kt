package com.example.shoppieeclient.data.home.account.repository


import android.net.Uri
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.shoppieeclient.domain.home.account.repository.AccountsCloudinaryRepo
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val TAG = "AccountsCloudinaryRepoI"
class AccountsCloudinaryRepoImpl: AccountsCloudinaryRepo {
    override suspend fun uploadImage(imageUri: Uri, userName: String): String {
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { continuation ->

                try {
                    val folderName = "users/$userName"
                    // Assign public ID to replace existing image
                    val publicId = "profile_${System.currentTimeMillis()}"
//                    val publicId = "profilePic"
                    Log.e(TAG, "Starting upload for $userName")
                    MediaManager.get().upload(imageUri)
                        .unsigned(Constants.UPLOAD_PRESET_CLOUDINARY)
                        .option("folder", folderName)
                        .option("public_id", publicId)
                        .callback(object : UploadCallback{
                            override fun onStart(requestId: String?) {
                                Log.d(TAG, "Upload started: $requestId")
                            }

                            override fun onProgress(
                                requestId: String?,
                                bytes: Long,
                                totalBytes: Long
                            ) {
                                Log.d(TAG, "Uploading progress: $bytes/$totalBytes")
                            }

                            override fun onSuccess(
                                requestId: String?,
                                resultData: Map<*, *>?
                            ) {
                                val url = resultData?.get("url").toString()
                                continuation.resume(url)
                            }

                            override fun onError(
                                requestId: String?,
                                error: ErrorInfo?
                            ) {
                                val errorMsg = error?.description ?: "Unknown error"
                                Log.e(TAG, "Upload error: $errorMsg")
                                continuation.resumeWithException(Exception(errorMsg))
                            }

                            override fun onReschedule(
                                requestId: String?,
                                error: ErrorInfo?
                            ) {
                                Log.w(TAG, "Upload rescheduled")
                            }

                        }).dispatch()
                } catch (e: Exception) {
                    Log.e(TAG, "Exception during upload: ${e.message}")
                    continuation.resumeWithException(e)
                }
            }
        }
    }
}