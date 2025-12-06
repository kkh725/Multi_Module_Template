plugins {
    id("java-library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.multi.module.hilt)
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

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.core)
}

