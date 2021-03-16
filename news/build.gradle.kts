plugins {
    id ("com.android.library")
    id("kotlin-android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs")

//    id("kotlin-android")
//    id("androidx.navigation.safeargs.kotlin")

}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.imageLibs)

    implementation(project(":common"))
    implementation(project(":business"))
    implementation(project(":dtos"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")

}