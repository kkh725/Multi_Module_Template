plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.hilt)
}

android {
    namespace = "com.kkh.data"
}

dependencies {
    implementation(project(":core:datastore")) // core 모듈 내의 dataStore 모듈을 의존성으로 추가
    implementation(project(":core:network"))   // core 모듈 내의 network 모듈을 의존성으로 추가
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
}