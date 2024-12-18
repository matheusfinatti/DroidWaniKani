pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

includeBuild("build-logic")

rootProject.name = "DroidWaniKani"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":core:network")
include(":core:ui")
include(":core:consts")
include(":core:models")
include(":core:database")
include(":features:login")
include(":features:home")
include(":features:levels")
include(":subject")
include(":app")
