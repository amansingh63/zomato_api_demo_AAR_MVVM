package com.amansingh63.zomatoapidemo.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object KotlinxJson {

    val instance: Json by lazy {
        Json(
            JsonConfiguration(
                ignoreUnknownKeys = true,
                isLenient = true
            )
        )
    }
}