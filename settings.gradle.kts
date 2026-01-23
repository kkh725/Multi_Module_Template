pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        flatDir {
            dirs("libs")
        }
    }
}

rootProject.name = "kkh-multi-module-template"
include(":app")

include(":feature:test")

include(":core:network")
include(":core:datastore")
include(":core:domain")
include(":core:data")
include(":core:common")
include(":core:database")
include(":core:designsystem")
include(":core:navigation")
include(":feature:auth")