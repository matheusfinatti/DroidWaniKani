package com.mfinatti.wanikanisimple.user.domain

import java.util.UUID

@JvmInline
value class UserId private constructor(val value: String) {

    companion object {
        fun from(value: String): Result<UserId> =
            when {
                value.isEmpty() -> Result.failure(IllegalArgumentException("Empty UserId"))
                else -> runCatching {
                    UUID.fromString(value)
                    UserId(value)
                }
            }
    }
}
