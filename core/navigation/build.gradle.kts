plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)
}