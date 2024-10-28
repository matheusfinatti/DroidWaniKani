package com.mfinatti.wanikanisimple.models.types

enum class ReadingType {
    kunyomi, nanori, onyomi;

    companion object {
        fun from(value: String?): ReadingType =
            when (value) {
                "kunyomi" -> kunyomi
                "nanori" -> nanori
                "onyomi" -> onyomi
                else -> error("Unknown reading type: $value")
            }
    }
}
