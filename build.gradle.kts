// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.android.application") version "7.0.2" apply false
    id("com.android.library") version "7.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
    id("com.google.dagger.hilt.android") version "2.40.5" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}