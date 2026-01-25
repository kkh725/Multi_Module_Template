plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:database")) //커밋이 달라졌을까?
}