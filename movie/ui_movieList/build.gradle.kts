plugins {
    id("example.android.library.compose")
    id("example.android.components")
    id("wallpaper.android.hilt")
    kotlin("kapt")
}

dependencies{

    implementation(project(":core"))
    implementation(project(":components"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))



    implementation(libs.sqldelight.android.driver)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.compose)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

}