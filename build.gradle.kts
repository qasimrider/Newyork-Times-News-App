// Top-level build file where you can add configuration options common to all sub-projects/modules.

//plugins {
//    kotlin("android") version "1.4.20" apply false
//}

buildscript {
//    val kotlin_version by extra("1.4.31")
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.1.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}")
        classpath ("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        maven(url = "https://maven.google.com/")
        google()
        jcenter()
        mavenCentral()
//        mavenLocal()


        maven(url = "https://dl.bintray.com/ekito/koin")
    }
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


apply("config.gradle")

