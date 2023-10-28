pluginManagement {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "ynaa-fagdag"