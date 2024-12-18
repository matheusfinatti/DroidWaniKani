plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.wk.detekt)
    alias(libs.plugins.wk.common.hilt)
    alias(libs.plugins.wk.android.application.compose)
    alias(libs.plugins.wk.android.room)
}

android {
    namespace = "com.mfinatti.wanikanisimple"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mfinatti.wanikanisimple"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core
    implementation(projects.core.network)
    implementation(projects.core.ui)
    implementation(projects.core.consts)
    implementation(projects.core.models)
    implementation(projects.core.database)

    // Features
    implementation(projects.features.login)
    implementation(projects.features.home)
    implementation(projects.features.levels)

    // Enable core library desugaring
    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
