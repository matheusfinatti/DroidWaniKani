plugins {
    id("wk.android.feature")
}

android {
    namespace = "wk.feature.home"
}

dependencies {
    implementation(projects.core.models)
    implementation(projects.core.database)
    implementation(projects.core.consts)
}