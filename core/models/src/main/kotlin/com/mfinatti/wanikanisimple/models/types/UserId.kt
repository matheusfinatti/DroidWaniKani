package com.mfinatti.wanikanisimple.models.types

import java.util.UUID

@JvmInline
value class UserId private constructor(val value: String) {

    companion object {
        fun from(value: String?): Result<UserId> =
            when {
                value == null -> Result.failure(Errors.Null)
                value.isEmpty() -> Result.failure(Errors.Empty)
                else -> runCatching {
                    UUID.fromString(value)
                    UserId(value)
                }
            }
    }

    sealed class Errors(message: String) : Exception(message) {
        data object Null : Errors("Null UserId")
        data object Empty : Errors("UserId cannot be empty")
    }
}
