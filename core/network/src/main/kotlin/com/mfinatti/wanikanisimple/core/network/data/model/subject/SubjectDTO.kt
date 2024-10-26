package com.mfinatti.wanikanisimple.core.network.data.model.subject

import com.mfinatti.wanikanisimple.core.network.data.model.meaning.AuxiliaryMeaningDTO
import com.mfinatti.wanikanisimple.core.network.data.model.meaning.MeaningDTO

interface SubjectDTO {
    val auxiliaryMeanings: List<AuxiliaryMeaningDTO>?
    val characters: String?
    val createdAt: String
    val documentUrl: String
    val hiddenAt: String?
    val lessonPosition: Int
    val level: Int
    val meaningMnemonic: String
    val meanings: List<MeaningDTO>
    val slug: String
    val spacedRepetitionSystemId: Int
}