package com.kkh.multimodule.convention

import com.android.build.api.dsl.TestExtension
import com.kkh.multimodule.convention.extensions.configureKotlinAndroid
import com.kkh.multimodule.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.test")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "de.mannodermaus.android-junit5")

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                defaultConfig.testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
            }

            dependencies{
                "androidTestImplementation"(libs.findLibrary("androidx-espresso-core").get())
                "androidTestImplementation"(libs.findLibrary("junit-jupiter-api").get())
                "androidTestImplementation"(libs.findLibrary("android-junit5-core").get())
                "androidTestRuntimeOnly"(libs.findLibrary("android-junit5-runner").get())

                "testImplementation"(libs.findLibrary("junit-jupiter").get())
            }
        }
    }
}
