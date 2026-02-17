plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.kkh.designsystem"
}

dependencies {
    implementation(project(":core:common"))

    api(libs.androidx.material3)
}
