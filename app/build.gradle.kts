plugins {
    alias(libs.plugins.multi.module.android.application)
    alias(libs.plugins.multi.module.android.compose)
    alias(libs.plugins.multi.module.hilt)
    alias(libs.plugins.multi.module.network)
}

dependencies {
    implementation(project(":feature:auth"))
    implementation(project(":feature:test"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
}
