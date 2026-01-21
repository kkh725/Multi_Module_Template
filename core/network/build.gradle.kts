plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.network)
    alias(libs.plugins.multi.module.hilt)
}

val localProps = rootProject.file("local.properties")
    .reader()
    .useLines { lines ->
        lines.find { it.startsWith("KKH_SERVER_BASE_URL=") }
            ?.substringAfter("=")
            ?.trim()
    } ?: throw GradleException("BASE_URL not found")

android{
    namespace = "com.kkh.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "KKH_SERVER_BASE_URL", "\"$localProps\"")
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
}