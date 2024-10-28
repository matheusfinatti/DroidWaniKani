package com.mfinatti.wanikanisimple.navigation

import androidx.annotation.StringRes
import com.mfinatti.wanikanisimple.R

enum class TopLevelDestination(
    val route: String,
    @StringRes val labelResId: Int,
) {
    home(
        route = "home",
        labelResId = R.string.drawer_destination_home,
    ),
    levels(
        route = "levels",
        labelResId = R.string.drawer_destination_levels,
    ),
}
