package com.mfinatti.wanikanisimple.models.data

import com.mfinatti.wanikanisimple.models.types.SubjectId
import java.time.Instant

data class Review(
    val availableAt: Instant,
    val subjectIds: List<SubjectId>,
)
