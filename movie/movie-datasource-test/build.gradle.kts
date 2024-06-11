plugins {
    id("example.movie.datasoruce")
}


android {
    namespace = "com.example.datasource.test"
}


dependencies{
    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-datasource"))
}

sqldelight{
    databases.create("MovieDatabase"){
        packageName.set("com.example.movie_datasource.cache")
        sourceFolders= listOf("sqldelight")
    }
}