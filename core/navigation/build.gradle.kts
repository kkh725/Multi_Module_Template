plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.multimodule.navigaiton"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.navigation.ui)
    implementation(libs.kotlinx.serialization.json)
}