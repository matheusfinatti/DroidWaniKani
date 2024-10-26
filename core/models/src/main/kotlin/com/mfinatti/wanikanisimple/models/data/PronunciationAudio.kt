package com.mfinatti.wanikanisimple.models.data

data class PronunciationAudio(
    val url: String,
    val contentType: String,
    val metadata: PronunciationAudioMetadata,
)
