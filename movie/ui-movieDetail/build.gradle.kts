plugins {
    id("example.android.application.compose")
    id("example.android.components")
}

android {
    namespace = "com.example.ui_moviedetail"
}

dependencies{

    implementation(project(":core"))
    implementation(project(":components"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))

}