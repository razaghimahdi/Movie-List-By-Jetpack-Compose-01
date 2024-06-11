plugins {
    id("example.android.library.compose")
    id("example.android.library")
    id("example.android.hilt")
}

android {
    namespace = "com.example.ui_moviedetail"
}

dependencies{

    implementation(project(":core"))
    implementation(project(":components"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.bundles.compose)

}