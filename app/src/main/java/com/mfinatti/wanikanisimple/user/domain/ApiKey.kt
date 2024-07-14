package com.mfinatti.wanikanisimple.user.domain

import java.util.UUID

@JvmInline
value class ApiKey private constructor(val value: String) {

    companion object {
        fun from(value: String?): Result<ApiKey> =
            when {
                value == null -> Result.failure(Errors.Null)
                value.isEmpty() -> Result.failure(Errors.Empty)
                else -> runCatching {
                    UUID.fromString(value)
                    ApiKey(value)
                }
            }
    }

    sealed class Errors(message: String) : Exception(message) {
        data object Null : Errors("Null ApiKey")
        data object Empty : Errors("ApiKey cannot be empty")
    }
}