plugins {
    id("java-library")
    `kotlin-dsl`             // Kotlin DSL 플러그인
    `java-gradle-plugin`     // Gradle Plugin 개발용 플러그인
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

//org. gradle import를 위한 의존성.
dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.compose.compiler.gradle.plugin)
    implementation(libs.jetbrains.kotlin.gradle.plugin)
    implementation(libs.kotlinx.serialization)

    compileOnly(libs.room.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.multi.module.android.compose.get().pluginId
            implementationClass =
                "com.kkh.multimodule.convention.AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.multi.module.android.application.get().pluginId
            implementationClass =
                "com.kkh.multimodule.convention.AndroidApplicationConventionPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.multi.module.android.feature.get().pluginId
            implementationClass = "com.kkh.multimodule.convention.AndroidFeatureConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.multi.module.hilt.get().pluginId
            implementationClass = "com.kkh.multimodule.convention.HiltConventionPlugin"
        }

        register("network") {
            id = libs.plugins.multi.module.network.get().pluginId
            implementationClass = "com.kkh.multimodule.convention.NetWorkConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.multi.module.library.get().pluginId
            implementationClass =
                "com.kkh.multimodule.convention.AndroidLibraryConventionPlugin"
        }

        register("androidRoom") {
            id = libs.plugins.multi.module.room.get().pluginId
            implementationClass = "com.kkh.multimodule.convention.AndroidRoomConventionPlugin"
        }
    }
}