plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.multimodule.datastore"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.datastore)
}