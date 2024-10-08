import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.mfinatti.wkdroid.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    compileOnly(libs.gradle.android.plugin)
    compileOnly(libs.gradle.kotlin)
    compileOnly(libs.gradle.android.tools.common)
    compileOnly(libs.gradle.ksp)
    compileOnly(libs.gradle.compose)
    compileOnly(libs.gradle.room)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "wk.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "wk.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "wk.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("hilt") {
            id = "wk.common.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("room") {
            id = "wk.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}
