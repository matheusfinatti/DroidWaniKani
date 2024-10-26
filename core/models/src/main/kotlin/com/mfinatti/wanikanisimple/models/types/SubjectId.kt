package com.mfinatti.wanikanisimple.models.types

@JvmInline
value class SubjectId private constructor(val value: Int) {

    companion object {
        fun from(value: Int?): Result<SubjectId> =
            when {
                value == null -> Result.failure(Errors.Null)
                value < 0 -> Result.failure(Errors.Invalid)
                else -> Result.success(SubjectId(value))
            }
    }

    sealed class Errors(message: String) : Exception(message) {
        data object Null : Errors("Null UserId")
        data object Invalid : Errors("SubjectId cannot be < 0")
    }
}
