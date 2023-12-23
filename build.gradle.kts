// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.49" apply false
}

buildscript{
    dependencies{
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.49")

        val navVersion = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}