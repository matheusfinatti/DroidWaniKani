plugins {
    id("wk.android.feature")
}

android {
    namespace = "com.mfinatti.wanikanisimple.levels"
}

dependencies {
    implementation(projects.core.models)
    implementation(projects.core.database)
    implementation(projects.core.consts)
    implementation(projects.subject)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
}