// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
}



buildscript {
    dependencies {
        classpath(libs.dagger.hilt.gradel)
    }
}



tasks.register("clean") {
    group = "clean"
    description = "Deletes the build directory"

    doLast {
        delete(rootProject.buildDir)
    }
}