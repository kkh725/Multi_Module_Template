package com.kkh.convention

import com.android.build.api.dsl.LibraryExtension
import com.kkh.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "multi.module-library")
            apply(plugin = "multi.module.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            apply(plugin = "com.github.skydoves.compose.stability.analyzer")

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(project(":core:navigation"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:domain"))
                "implementation"(project(":core:common"))

                "implementation"(libs.findLibrary("hilt-navigation-compose").get())
                "implementation"(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                "implementation"(libs.findLibrary("androidx-navigation3-runtime").get())
            }
        }
    }
}
