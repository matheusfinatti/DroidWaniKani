package com.mfinatti.wanikanisimple.user.domain

import java.util.UUID

@JvmInline
value class ApiKey private constructor(val value: String) {

    companion object {
        fun from(value: String?): Result<ApiKey> =
            when {
                value == null -> Result.failure(IllegalArgumentException("Null ApiKey"))
                value.isEmpty() -> Result.failure(IllegalArgumentException("Empty ApiKey"))
                else -> runCatching {
                    UUID.fromString(value)
                    ApiKey(value)
                }
            }
    }
}