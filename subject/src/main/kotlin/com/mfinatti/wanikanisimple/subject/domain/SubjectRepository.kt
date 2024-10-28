package com.mfinatti.wanikanisimple.subject.domain

import com.mfinatti.wanikanisimple.models.types.Level
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    fun fetchSubjects(level: Level): Flow<SubjectState>
}
