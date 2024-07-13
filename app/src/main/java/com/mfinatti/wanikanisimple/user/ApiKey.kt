package com.mfinatti.wanikanisimple.user

@JvmInline
value class ApiKey private constructor(val value: String) {

    companion object {
        fun String.into(): Result<ApiKey> =
            if (this.isEmpty()) {
                Result.failure(IllegalArgumentException("ApiKey can't be empty"))
            } else {
                Result.success(ApiKey(this))
            }
    }
}