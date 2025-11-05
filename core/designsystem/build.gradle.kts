plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.kkh.multimodule.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(libs.androidx.material3)
    api(libs.androidx.material3.adaptive)
    api(libs.androidx.material3.navigationSuite)

    implementation(libs.coil.kt.compose)
}