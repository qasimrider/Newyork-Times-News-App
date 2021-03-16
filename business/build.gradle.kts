plugins {
    id ("com.android.library")
    id("kotlin-android")
    id ("kotlin-kapt")

}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":repositories"))
    implementation(project(":dtos"))

}