package com.mfinatti.wanikanisimple.subject.data.mapper

import com.mfinatti.wanikanisimple.core.network.data.model.audio.PronunciationAudioDTO
import com.mfinatti.wanikanisimple.core.network.data.model.image.CharacterImageDTO
import com.mfinatti.wanikanisimple.core.network.data.model.meaning.AuxiliaryMeaningDTO
import com.mfinatti.wanikanisimple.core.network.data.model.meaning.MeaningDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.ContextSentenceDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanaVocabularyDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanjiDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.RadicalDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.ReadingDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTOWrapper
import com.mfinatti.wanikanisimple.core.network.data.model.subject.VocabularyDTO
import com.mfinatti.wanikanisimple.models.data.AuxiliaryMeaning
import com.mfinatti.wanikanisimple.models.data.CharacterImage
import com.mfinatti.wanikanisimple.models.data.CharacterImageMetadata
import com.mfinatti.wanikanisimple.models.data.ContextSentence
import com.mfinatti.wanikanisimple.models.data.KanaVocabulary
import com.mfinatti.wanikanisimple.models.data.Kanji
import com.mfinatti.wanikanisimple.models.data.Meaning
import com.mfinatti.wanikanisimple.models.data.PronunciationAudio
import com.mfinatti.wanikanisimple.models.data.PronunciationAudioMetadata
import com.mfinatti.wanikanisimple.models.data.Radical
import com.mfinatti.wanikanisimple.models.data.Reading
import com.mfinatti.wanikanisimple.models.data.Subject
import com.mfinatti.wanikanisimple.models.data.Vocabulary
import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.MeaningType
import com.mfinatti.wanikanisimple.models.types.ReadingType
import com.mfinatti.wanikanisimple.models.types.SubjectId
import com.mfinatti.wanikanisimple.models.types.SubscriptionType
import java.time.Instant

fun SubjectDTOWrapper.toSubject(): Subject =
    when (val data = this.data) {
        is KanjiDTO -> data.toKanji(id)
        is RadicalDTO -> data.toRadical(id)
        is VocabularyDTO -> data.toVocabulary(id)
        is KanaVocabularyDTO -> data.toKanaVocabulary(id)
        else -> error("Unknown Subject type")
    }

fun KanjiDTO.toKanji(id: Int): Kanji =
    Kanji(
        id = SubjectId.from(id).getOrThrow(),
        auxiliaryMeanings = auxiliaryMeanings?.map { it.toAuxiliaryMeaning() } ?: error("Kanji without auxiliary meanings"),
        characters = characters ?: error("Kanji without characters"),
        createdAt = Instant.parse(createdAt),
        documentUrl = documentUrl,
        hiddenAt = hiddenAt?.let { Instant.parse(it) },
        lessonPosition = lessonPosition,
        level = Level.from(level, SubscriptionType.lifetime).getOrThrow(),
        meaningMnemonic = meaningMnemonic,
        meanings = meanings.map { it.toMeaning() },
        slug = slug,
        spacedRepetitionSystemId = spacedRepetitionSystemId,
        amalgamationSubjectIds = amalgamationSubjectIds.map { SubjectId.from(it).getOrThrow() },
        componentSubjectIds = componentSubjectIds.map { SubjectId.from(it).getOrThrow() },
        meaningHint = meaningHint ?: error("Kanji without meaning hint"),
        readingHint = readingHint ?: error("Kanji without reading hint"),
        readings = readings.map { it.toReading() },
        visuallySimilarSubjectIds = visuallySimilarSubjectIds.map { SubjectId.from(it).getOrThrow() },
    )

fun RadicalDTO.toRadical(id: Int): Radical =
    Radical(
        id = SubjectId.from(id).getOrThrow(),
        auxiliaryMeanings = auxiliaryMeanings?.map { it.toAuxiliaryMeaning() } ?: error("Radical without auxiliary meanings"),
        characters = characters,
        createdAt = Instant.parse(createdAt),
        documentUrl = documentUrl,
        hiddenAt = hiddenAt?.let { Instant.parse(it) },
        lessonPosition = lessonPosition,
        level = Level.from(level, SubscriptionType.lifetime).getOrThrow(),
        meaningMnemonic = meaningMnemonic,
        meanings = meanings.map { it.toMeaning() },
        slug = slug,
        spacedRepetitionSystemId = spacedRepetitionSystemId,
        amalgamationSubjectIds = amalgamationSubjectIds.map { SubjectId.from(it).getOrThrow() },
        characterImages = characterImages.map { it.toCharacterImage() },
    )

fun VocabularyDTO.toVocabulary(id: Int): Vocabulary =
    Vocabulary(
        id = SubjectId.from(id).getOrThrow(),
        auxiliaryMeanings = auxiliaryMeanings?.map { it.toAuxiliaryMeaning() } ?: error("Vocabulary without auxiliary meanings"),
        characters = characters,
        createdAt = Instant.parse(createdAt),
        documentUrl = documentUrl,
        hiddenAt = hiddenAt?.let { Instant.parse(it) },
        lessonPosition = lessonPosition,
        level = Level.from(level, SubscriptionType.lifetime).getOrThrow(),
        meaningMnemonic = meaningMnemonic,
        meanings = meanings.map { it.toMeaning() },
        slug = slug,
        spacedRepetitionSystemId = spacedRepetitionSystemId,
        contextSentences = contextSentences.map { it.toContextSentence() },
        readingHint = readingHint,
        partsOfSpeech = partsOfSpeech,
        pronunciationAudios = pronunciationAudios.map { it.toPronunciationAudio() },
        readings = readings.map { it.toReading() },
        readingMnemonic = readingMnemonic,
    )

fun KanaVocabularyDTO.toKanaVocabulary(id: Int): KanaVocabulary =
    KanaVocabulary(
        id = SubjectId.from(id).getOrThrow(),
        auxiliaryMeanings = auxiliaryMeanings?.map { it.toAuxiliaryMeaning() } ?: error("Vocabulary without auxiliary meanings"),
        characters = characters,
        createdAt = Instant.parse(createdAt),
        documentUrl = documentUrl,
        hiddenAt = hiddenAt?.let { Instant.parse(it) },
        lessonPosition = lessonPosition,
        level = Level.from(level, SubscriptionType.lifetime).getOrThrow(),
        meaningMnemonic = meaningMnemonic,
        meanings = meanings.map { it.toMeaning() },
        slug = slug,
        spacedRepetitionSystemId = spacedRepetitionSystemId,
        contextSentences = contextSentences.map { it.toContextSentence() },
        partsOfSpeech = partsOfSpeech,
        pronunciationAudios = pronunciationAudios.map { it.toPronunciationAudio() },
    )

fun AuxiliaryMeaningDTO.toAuxiliaryMeaning(): AuxiliaryMeaning =
    AuxiliaryMeaning(
        meaning = meaning,
        type = when (type) {
            "blacklist" -> MeaningType.blacklist
            "whitelist" -> MeaningType.whitelist
            else -> error("Invalid Meaning Type: $type")
        }
    )

fun MeaningDTO.toMeaning(): Meaning =
    Meaning(
        meaning = meaning,
        primary = primary,
        acceptedAnswer = acceptedAnswer,
    )

fun ReadingDTO.toReading(): Reading =
    Reading(
        reading = reading,
        primary = primary,
        acceptedAnswer = acceptedAnswer,
        type = ReadingType.from(type)
    )

fun CharacterImageDTO.toCharacterImage(): CharacterImage =
    CharacterImage(
        url = url,
        metadata = CharacterImageMetadata(),
        contentType = contentType
    )

fun ContextSentenceDTO.toContextSentence(): ContextSentence =
    ContextSentence(
        en = en,
        ja = ja,
    )

fun PronunciationAudioDTO.toPronunciationAudio(): PronunciationAudio =
    PronunciationAudio(
        url = url,
        contentType = contentType,
        metadata = PronunciationAudioMetadata(
            gender = metadata.gender,
            sourceId = metadata.sourceId,
            pronunciation = metadata.pronunciation,
            voiceActorId = metadata.voiceActorId,
            voiceActorName = metadata.voiceActorName,
            voiceDescription = metadata.voiceDescription
        )
    )
