package com.amansingh63.zomatoapidemo.util.network

import android.net.Uri
import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.io.File


/**
 * Developer: Click Labs
 *
 *
 * Retrofit utility class
 */
object RetrofitUtils {

    /**
     * Forms request body from string
     *
     * @param value content which need to convert into request body
     * @return formed request body object
     */
    internal fun getRequestBodyFromString(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }


    /**
     * Forms a part body from file
     *
     * @param key  parameter name
     * @param file the file to include as part of request body
     * @return formed part body
     */
    fun getPartBodyFromFile(key: String, file: File): MultipartBody.Part {

        // create RequestBody instance from file
        val requestFile = file.asRequestBody(
            getMimeType(
                file
            ).toMediaTypeOrNull()
        )

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(key, file.name, requestFile)

    }


    /**
     * Get the mime type
     *
     * @param file file for which mime type is required
     * @return the mimeType of the passed file
     */
    internal fun getMimeType(file: File): String {
        var mimeType = "image/png"
        try {
            val selectedUri = Uri.fromFile(file)
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(selectedUri.toString())
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)!!
        } catch (error: Throwable) {
            Timber.e(error)
        }

        return mimeType
    }
}
