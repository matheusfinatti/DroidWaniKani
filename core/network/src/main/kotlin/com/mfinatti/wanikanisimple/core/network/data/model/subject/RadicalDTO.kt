package com.mfinatti.wanikanisimple.core.network.data.model.subject

import com.mfinatti.wanikanisimple.core.network.data.model.image.CharacterImageDTO
import com.mfinatti.wanikanisimple.core.network.data.model.meaning.AuxiliaryMeaningDTO
import com.mfinatti.wanikanisimple.core.network.data.model.meaning.MeaningDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RadicalDTO(
    @Json(name = "auxiliary_meanings")
    override val auxiliaryMeanings: List<AuxiliaryMeaningDTO>?,
    @Json(name = "characters")
    override val characters: String?,
    @Json(name = "created_at")
    override val createdAt: String,
    @Json(name = "document_url")
    override val documentUrl: String,
    @Json(name = "hidden_at")
    override val hiddenAt: String?,
    @Json(name = "lesson_position")
    override val lessonPosition: Int,
    @Json(name = "level")
    override val level: Int,
    @Json(name = "meaning_mnemonic")
    override val meaningMnemonic: String,
    @Json(name = "meanings")
    override val meanings: List<MeaningDTO>,
    @Json(name = "slug")
    override val slug: String,
    @Json(name = "spaced_repetition_system_id")
    override val spacedRepetitionSystemId: Int,
    @Json(name = "amalgamation_subject_ids")
    val amalgamationSubjectIds: List<Int>,
    @Json(name = "character_images")
    val characterImages: List<CharacterImageDTO>,
) : SubjectDTO
