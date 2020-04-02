package com.amansingh63.zomatoapidemo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ApiError(
    val code: Int = 0,
    val message: String = "Api Error",
    val status: String = ""
) : Parcelable {
    companion object {
        val NO_INTERNET = ApiError(1000, "No Internet Connection")
    }
}