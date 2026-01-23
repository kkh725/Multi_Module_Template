package com.kkh.convention

import com.android.build.gradle.LibraryExtension
import com.kkh.convention.extensions.configureKotlinAndroid
import com.kkh.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "com.android.library")
        apply(plugin = "org.jetbrains.kotlin.android")
        apply(plugin = "de.mannodermaus.android-junit5")

        // module에 test 관련 파일 없으면 테스트 진행하지 않는다.
        tasks.withType<Test>().configureEach {
            failOnNoDiscoveredTests.set(false)

            // test file에 없어도, 폴더 생성. test 통과용.
            doFirst {
                reports.html.outputLocation.get().asFile.mkdirs()
                reports.junitXml.outputLocation.get().asFile.mkdirs()
            }
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)

            defaultConfig.testInstrumentationRunner =
                "androidx.test.runner.AndroidJUnitRunner"
            defaultConfig.testInstrumentationRunnerArguments["runnerBuilder"] =
                "de.mannodermaus.junit5.AndroidJUnit5Builder"

            testOptions.animationsDisabled = true

            resourcePrefix =
                path.split("""\W""".toRegex())
                    .drop(1)
                    .distinct()
                    .joinToString("_")
                    .lowercase() + "_"

            testOptions {
                unitTests.all {
                    it.useJUnitPlatform()
                }
            }
        }

        dependencies {
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
            add("androidTestImplementation", libs.findLibrary("junit-jupiter-api").get())
            add("androidTestImplementation", libs.findLibrary("android-junit5-core").get())
            add("androidTestRuntimeOnly", libs.findLibrary("android-junit5-runner").get())
            add("testImplementation", libs.findLibrary("junit-jupiter").get())
            add("implementation", libs.findLibrary("coroutines-core").get())
            add("testImplementation", libs.findLibrary("coroutines-test").get())
            add("testImplementation", "org.junit.platform:junit-platform-launcher")
        }
    }
}

