plugins {
    id("example.android.application.compose")
    id("example.android.components")
}
android {
    namespace = "com.example.components"
}

dependencies {
    implementation(project(":core"))
}