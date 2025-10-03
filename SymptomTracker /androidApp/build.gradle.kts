plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleServices)
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
    implementation(project(":shared"))


  // Use Compose BOM for Android Compose dependencies
    implementation(platform(libs.compose.bom))

    // Android Compose dependencies (no version needed due to BOM)
    implementation(libs.activity.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.material3)

    // Firebase dependencies
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.activity.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.common)
    implementation(libs.firebase.firestore)

    debugImplementation(libs.compose.ui.tooling)
}
