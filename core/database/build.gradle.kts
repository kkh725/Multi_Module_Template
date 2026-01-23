plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.hilt)
    alias(libs.plugins.multi.module.room)
}

android {
    namespace = "com.kkh.database"
}

dependencies {
    implementation(project(":core:common"))
}
