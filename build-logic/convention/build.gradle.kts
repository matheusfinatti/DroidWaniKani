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
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "wk.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
