plugins {
    id("example.android.library")
}

android {
    namespace = "com.example.domain"
}

dependencies{
    implementation(project(":core"))
}