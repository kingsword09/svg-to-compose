enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
  repositories {
    google {
      content {
        this.
        includeGroupByRegex("com\\.android.*")
      }
    }
    mavenCentral()
  }
}

rootProject.name = "svg-to-compose"

