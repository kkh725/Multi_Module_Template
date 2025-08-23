plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.multi.module.hilt)
    alias(libs.plugins.multi.module.network)
}

android {
    namespace = "com.kkh.multimodule.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}