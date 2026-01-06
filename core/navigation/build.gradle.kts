plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.multimodule.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.ui)
    implementation(libs.kotlinx.serialization.json)
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)
}