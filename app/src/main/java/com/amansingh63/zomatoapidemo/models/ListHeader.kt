package com.amansingh63.zomatoapidemo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ListHeader(val title: String) : Parcelable