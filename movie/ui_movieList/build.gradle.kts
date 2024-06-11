plugins {
    id("example.android.library.compose")
    id("example.android.library")
    id("example.android.hilt")
   // alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.ui_movielist"
}

dependencies{

    implementation(project(":core"))
    implementation(project(":components"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))



    implementation(libs.sqldelight.android.driver)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.bundles.compose)

}