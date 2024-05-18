// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false

    //ksp
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false

    //Hilt
    id("com.google.dagger.hilt.android") version "2.44" apply false
}