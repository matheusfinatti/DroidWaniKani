plugins {
    alias(libs.plugins.wk.android.library)
    alias(libs.plugins.wk.common.hilt)
}

android {
    namespace = "wk.core.network"
}

dependencies {
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okttp.logging.interceptor)

    // Moshi
    implementation(libs.moshi)
}