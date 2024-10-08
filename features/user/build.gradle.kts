plugins {
    id("wk.android.library")
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.kapt") // TODO: Create an alias
    alias(libs.plugins.ksp)
}

android {
    namespace = "wk.core.network"
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okttp.logging.interceptor)

    // Moshi
    implementation(libs.moshi)
}