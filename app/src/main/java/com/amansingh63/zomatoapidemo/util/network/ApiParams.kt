package com.amansingh63.zomatoapidemo.util.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class ApiParams private constructor(builder: Builder) {


    var map = HashMap<String, String>()

    var multiPartMap = HashMap<String, RequestBody>()

    init {
        if (builder.multipartBody) {
            multiPartMap = builder.multiPartMap
        } else {
            this.map = builder.map
        }
    }


    /**
     * The type Builder.
     */
    /**
     * Instantiates a new Builder.
     */
    class Builder {
        /**
         * The Map.
         */
        val map = HashMap<String, String>()
        val multiPartMap = HashMap<String, RequestBody>()
        var multipartBody = false

        /**
         * Add a value to the map.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        fun add(key: String, value: Any?): Builder {
            if (value == null || value.toString().isEmpty()) {
                return this
            }
            if (multipartBody) {
                multiPartMap[key] =
                    RetrofitUtils.getRequestBodyFromString(value.toString())
            } else {
                map[key] = value.toString()
            }
            return this
        }

        fun setMultipart(): Builder {
            multipartBody = true
            return this
        }

        /**
         * Add a value to the map
         *
         * @param key          the key
         * @param value        the value
         * @param isAllowEmpty is empty allowed
         * @return the builder
         */
        fun add(key: String, value: Any?, isAllowEmpty: Boolean): Builder {
            if (!isAllowEmpty && (value == null || value.toString().isEmpty())) {
                return this
            }
            if (multipartBody) {
                multiPartMap[key] =
                    RetrofitUtils.getRequestBodyFromString(value.toString())
            } else {
                map[key] = value.toString()
            }
            return this
        }

        /**
         * Add file to map.
         *
         * @param key   the key
         * @param mFile the file to add
         * @return the builder
         */
        fun addFile(key: String, mFile: File?): Builder {
            if (mFile == null) {
                return this
            }
            multiPartMap[key + "\"; filename=\"" + mFile.name] =
                mFile.asRequestBody(RetrofitUtils.getMimeType(mFile).toMediaTypeOrNull())
            return this
        }

        /**
         * Add array of files to map
         *
         * @param key            the key
         * @param mFileArrayList the files array list
         * @return the builder
         */
        fun addArrayOfFiles(key: String, mFileArrayList: ArrayList<File>?): Builder {
            if (mFileArrayList == null || mFileArrayList.size == 0) {
                return this
            }

            for (i in mFileArrayList.indices) {
                multiPartMap[key + "\"; filename=\"" + mFileArrayList[i].name] =
                    mFileArrayList[i].asRequestBody(
                        RetrofitUtils.getMimeType(
                            mFileArrayList[i]
                        ).toMediaTypeOrNull()
                    )
            }
            return this
        }


        /**
         * Build common params.
         *
         * @return the common params
         */
        fun build(): ApiParams {
            return ApiParams(this)
        }
    }
}