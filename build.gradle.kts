// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.secrets.gradle.plugin) apply false
    id("io.gitlab.arturbosch.detekt").version("1.23.6")
}

buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}
