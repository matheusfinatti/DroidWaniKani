plugins {
    alias(libs.plugins.wk.android.library)
    alias(libs.plugins.wk.android.library.compose)
}

android {
    namespace = "wk.core.ui"
}

dependencies {
    api(libs.androidx.material3)
}