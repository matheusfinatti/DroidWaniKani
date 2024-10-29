package com.mfinatti.wanikanisimple.models.data

import com.mfinatti.wanikanisimple.models.types.ReadingType

data class Reading(
    val reading: String,
    val primary: Boolean,
    val acceptedAnswer: Boolean,
    val type: ReadingType?,
)
