package com.kkh.multimodule.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.let

fun <T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
    serializer: KSerializer<T>
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let { json.decodeFromString(serializer, it) }

    override fun parseValue(value: String): T = json.decodeFromString(serializer, value)

    override fun serializeAsValue(value: T): String =
        json.encodeToString(serializer, value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(serializer, value))
    }
}
