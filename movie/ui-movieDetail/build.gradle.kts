plugins {
    id("example.android.library.compose")
    id("example.android.components")
}

dependencies{

    implementation(project(":core"))
    implementation(project(":components"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))

}