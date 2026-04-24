import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "paper"

include("paper-api")
include("paper-server")

// Determine if we are running in a CI environment
val isCi = System.getenv("CI") != null

// Configure build cache
buildCache {
    local {
        isEnabled = !isCi
        // Keep up to 500MB of build cache entries locally
        removeUnusedEntriesAfterDays = 14
    }
}

// Configure Gradle Enterprise / Develocity if available
if (isCi) {
    logger.lifecycle("CI environment detected, local build cache disabled.")
} else {
    logger.lifecycle("Local build cache enabled (entries expire after 14 days).")
}
