package com.kkh.convention

// AndroidApplicationConventionPlugin.kt
import com.android.build.api.dsl.ApplicationExtension
import com.kkh.convention.extensions.configureKotlinAndroid
import com.kkh.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

//그래들에서 클래스를 플러그인으로 설정할수 있게끔 함.
class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {

                configureKotlinAndroid(this)

                defaultConfig {
                    namespace = libs.findVersion("projectApplicationId").get().toString()
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx-navigation3-runtime").get())
                "implementation"(libs.findLibrary("androidx-navigation3-ui").get())
            }
        }
    }
}