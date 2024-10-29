package com.mfinatti.wanikanisimple.levels.ui

import com.mfinatti.wanikanisimple.models.data.KanaVocabulary
import com.mfinatti.wanikanisimple.models.data.Kanji
import com.mfinatti.wanikanisimple.models.data.Radical
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.Vocabulary
import com.mfinatti.wanikanisimple.models.types.SubjectId

interface SubjectItem {
    val id: SubjectId
    val characters: String
    val meaning: String
    val reading: String?
}

data class RadicalItem(
    override val id: SubjectId,
    override val characters: String,
    override val meaning: String,
    override val reading: String? = null,
) : SubjectItem

data class KanjiItem(
    override val id: SubjectId,
    override val characters: String,
    override val meaning: String,
    override val reading: String,
) : SubjectItem

data class VocabularyItem(
    override val id: SubjectId,
    override val characters: String,
    override val meaning: String,
    override val reading: String,
) : SubjectItem

data class KanaVocabularyItem(
    override val id: SubjectId,
    override val characters: String,
    override val meaning: String,
    override val reading: String? = null,
) : SubjectItem

fun Subject.toSubjectItem(): SubjectItem = when (this) {
    is Radical -> RadicalItem(id, characters ?: "", meanings.find { it.primary }?.meaning ?: "")
    is Kanji -> KanjiItem(id, characters, meanings.find { it.primary}?.meaning ?: "", readings.find { it.primary }?.reading ?: "")
    is Vocabulary -> VocabularyItem(id, characters ?: "", meanings.find { it.primary}?.meaning ?: "", readings.find { it.primary }?.reading ?: "")
    is KanaVocabulary -> KanaVocabularyItem(id, characters ?: "", meanings.find { it.primary }?.meaning ?: "")
}