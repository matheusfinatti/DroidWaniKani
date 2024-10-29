package com.mfinatti.wanikanisimple.models.data

import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.SubjectId
import java.time.Instant

data class Radical(
    val id: SubjectId,
    val auxiliaryMeanings: List<AuxiliaryMeaning>,
    val characters: String?,
    val createdAt: Instant,
    val documentUrl: String,
    val hiddenAt: Instant?,
    val lessonPosition: Int,
    val level: Level,
    val meaningMnemonic: String,
    val meanings: List<Meaning>,
    val slug: String,
    val spacedRepetitionSystemId: Int,
    val amalgamationSubjectIds: List<SubjectId>,
    val characterImages: List<CharacterImage>,
) : Subject
