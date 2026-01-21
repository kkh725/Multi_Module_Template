package com.kkh.convention

import com.kkh.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class NetWorkConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                "implementation"(libs.findLibrary("retrofit").get())
                "implementation"(libs.findLibrary("retrofit-kotlin-serialization").get())

                "implementation"(libs.findLibrary("okhttp").get())
                "implementation"(libs.findLibrary("okhttp-logging").get())
                "implementation"(platform(libs.findLibrary("okhttp-bom").get()))

                "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}