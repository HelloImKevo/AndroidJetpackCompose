// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    // Dagger + Hilt
    // https://dagger.dev/hilt/gradle-setup
    // https://github.com/google/dagger/releases/tag/dagger-2.42
    id("com.google.dagger.hilt.android") version "2.42" apply false
}
