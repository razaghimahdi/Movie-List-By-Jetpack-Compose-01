plugins {
    id("example.android.library")
}

android {
    namespace = "com.example.core"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}