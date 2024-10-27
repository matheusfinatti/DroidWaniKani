plugins {
    id("wk.android.feature")
}

android {
    namespace = "com.mfinatti.wanikanisimple.home"
}

dependencies {
    implementation(projects.core.models)
    implementation(projects.core.database)
    implementation(projects.core.consts)

    testImplementation(libs.coroutines.test)
}