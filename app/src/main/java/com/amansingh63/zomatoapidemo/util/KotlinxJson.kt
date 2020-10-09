package com.amansingh63.zomatoapidemo.util

import kotlinx.serialization.json.Json

object KotlinxJson {

    val instance: Json by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}