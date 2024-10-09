plugins {
    alias(libs.plugins.wk.android.library)
    alias(libs.plugins.wk.android.room)
    alias(libs.plugins.wk.common.hilt)
}

android {
    namespace = "wk.core.database"
}
