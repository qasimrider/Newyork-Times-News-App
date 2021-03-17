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


    implementation(AppDependencies.testLibraries)
    implementation(AppDependencies.androidTestLibraries)

    implementation(project(":common"))
    implementation(project(":business"))
    implementation(project(":dtos"))

    kaptTest("androidx.databinding:databinding-compiler:7.0.0-alpha10")
    kaptAndroidTest("androidx.databinding:databinding-compiler:4.1.2")


}