package com.mfinatti.wanikanisimple.levels.ui

import androidx.annotation.StringRes
import com.mfinatti.wanikanisimple.levels.R

enum class LevelTier(
    @StringRes val labelRes: Int,
    val levels: IntRange,
) {
    pleasant(
        labelRes = R.string.features_levels_tier_pleasant,
        levels = 1..10
    ),
    painful(
        labelRes = R.string.features_levels_tier_painful,
        levels = 11..20
    ),
    death(
        labelRes = R.string.features_levels_tier_death,
        levels = 21..30
    ),
    hell(
        labelRes = R.string.features_levels_tier_hell,
        levels = 31..40
    ),
    paradise(
        labelRes = R.string.features_levels_tier_paradise,
        levels = 41..50
    ),
    reality(
        labelRes = R.string.features_levels_tier_reality,
        levels = 51..60
    )
}
