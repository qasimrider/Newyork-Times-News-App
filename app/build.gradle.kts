//import org.gradle.kotlin.dsl.dependencies
//import org.gradle.kotlin.dsl.kotlin
//import org.gradle.kotlin.dsl.*
//plugins {
//    id 'com.android.application'
//    id 'kotlin-android'
//}

plugins {
    id("com.android.application")
    id("kotlin-android")
    id ("kotlin-kapt")

//    kotlin("android.extensions")
//    id("androidx.navigation.safeargs")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.nytimes.newsapp"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
        testInstrumentationRunner(AppConfig.androidTestInstrumentation)

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }




    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}





dependencies {

//    //std lib
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":news"))
    implementation(project(":repositories"))
    implementation(project(":network"))
    implementation(project(":business"))

//    implementation("androidx.navigation:navigation-fragment-ktx:2.3.4")
//    implementation("androidx.navigation:navigation-ui-ktx:2.3.4")
//    implementation(AppDependencies.koinLibs)

}