package com.mfinatti.wanikanisimple.models.data

data class PronunciationAudioMetadata(
    val gender: String,
    val sourceId: Int,
    val pronunciation: String,
    val voiceActorId: Int,
    val voiceActorName: String,
    val voiceDescription: String,
)
