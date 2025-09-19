enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
      google()
      mavenCentral()
      gradlePluginPortal()
      maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    // Add other repositories if needed, like Compose for Desktop's specific one
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") // Often needed for Compose Multiplatform
  }
}

rootProject.name = "SymptomTracker"
include(":androidApp")
include(":shared")
