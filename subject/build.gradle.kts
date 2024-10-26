plugins {
    alias(libs.plugins.wk.android.library)
    alias(libs.plugins.wk.common.hilt)
}

android {
    namespace = "wk.subject"
}

dependencies {
    // Models
    implementation(projects.core.models)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(projects.core.consts)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okttp.logging.interceptor)

    // Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}