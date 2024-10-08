plugins {
    id("wk.android.library")
    id("wk.android.library.compose")
}

android {
    namespace = "wk.core.ui"
}

dependencies {
    api(libs.androidx.material3)
}