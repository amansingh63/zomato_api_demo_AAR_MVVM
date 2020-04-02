package com.amansingh63.zomatoapidemo.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import javax.inject.Inject

const val API_LIMIT_COUNT = "api_limit_count"

class AppPreferenceImpl @Inject constructor(
    context: Context,
    private val prefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
) : AppPreference {

    override var apiLimitCount: Int
        get() = prefs.getInt(API_LIMIT_COUNT, 50)
        set(value) = prefs.edit {
            putInt(API_LIMIT_COUNT, value)
        }


}