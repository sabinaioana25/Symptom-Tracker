plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.symptomtracker.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.example.symptomtracker.android"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.shared)

    // Use Compose BOM for Android Compose dependencies
    implementation(platform(libs.compose.bom))

    // Android Compose dependencies (no version needed due to BOM)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation("androidx.compose.ui:ui:1.10.0-alpha02")
    implementation("androidx.compose.ui:ui-tooling-preview:1.10.0-alpha02")
    implementation("androidx.compose.material3:material3:1.5.0-alpha03")
    debugImplementation("androidx.compose.ui:ui-tooling:1.10.0-alpha02")
}
