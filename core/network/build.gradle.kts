plugins {
    alias(libs.plugins.multi.module.library)
    alias(libs.plugins.multi.module.network)
    alias(libs.plugins.multi.module.hilt)
}

val localProps =
    rootProject
        .file("local.properties")
        .takeIf { it.exists() }
        ?.reader()
        ?.useLines { lines ->
            lines
                .find { it.startsWith("KKH_SERVER_BASE_URL=") }
                ?.substringAfter("=")
                ?.trim()
        } ?: ""

android {
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
}
