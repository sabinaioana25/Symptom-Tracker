@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
}

kotlin {
  applyDefaultHierarchyTemplate()

  androidTarget {
    compilations.all {
      compileTaskProvider.configure {
        compilerOptions {
          jvmTarget.set(JvmTarget.JVM_1_8)
        }
      }
    }
    publishLibraryVariants("release", "debug")
  }

  compilerOptions {
    optIn.add("kotlin.uuid.ExperimentalUuidApi")
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      //put your multiplatform dependencies here
      implementation(libs.atomic.fu)
      implementation(libs.kotlinx.coroutines.core)
    }
    commonTest.dependencies {
      implementation(libs.kotlin.test)
      implementation(libs.io.kotest)
      implementation(libs.kotlinx.coroutines.test)
    }
  }
}

android {
  namespace = "com.example.symptomtracker"
  compileSdk = 36
  defaultConfig {
    minSdk = 24
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    debug {
      isMinifyEnabled = false
      isShrinkResources = false
    }
    release {
      isMinifyEnabled = false
      isShrinkResources = false
    }
  }

  buildFeatures {
    viewBinding = true
  }

  lint {
    abortOnError = true
    lintConfig = file("lint.xml")
    targetSdk = 35
  }

  testOptions {
    targetSdk = 35
    animationsDisabled = true
    unitTests.all {
      it.useJUnitPlatform()
    }
  }
}
