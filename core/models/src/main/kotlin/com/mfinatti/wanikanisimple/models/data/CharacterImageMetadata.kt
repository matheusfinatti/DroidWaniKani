package com.mfinatti.wanikanisimple.models.data

data class CharacterImageMetadata(
    // note: Default value in Wanikani.
    // @see: https://docs.api.wanikani.com/20170710/#subject-data-structure
    val inlineStyles: Boolean = true,
)
