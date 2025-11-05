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

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
            }

            dependencies{
                "androidTestImplementation"(libs.findLibrary("androidx-junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx-espresso.core").get())
                "androidTestImplementation"(libs.findLibrary("androidx-ui-test-junit4").get())

                "testImplementation"(libs.findLibrary("junit").get())
            }
        }
    }
}
