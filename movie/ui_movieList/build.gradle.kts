plugins {
    id("example.android.application.compose")
    id("example.android.components")
    id("example.android.hilt")
    alias(libs.plugins.ksp)
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

}