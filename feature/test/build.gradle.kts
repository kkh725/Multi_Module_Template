plugins {
    alias(libs.plugins.multi.module.android.feature)
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.kkh.multimodule.feature.test"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}