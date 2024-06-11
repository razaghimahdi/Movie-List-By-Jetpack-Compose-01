plugins {
    id("example.movie.datasoruce")
}

android {
    namespace = "com.example.datasource"
}


dependencies{
    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))

    implementation(libs.bundles.network)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.sqldelight.runtime)
}

sqldelight{
    databases.create("MovieDatabase"){
        packageName.set("com.example.movie_datasource.cache")
        sourceFolders= listOf("sqldelight")
    }
}
