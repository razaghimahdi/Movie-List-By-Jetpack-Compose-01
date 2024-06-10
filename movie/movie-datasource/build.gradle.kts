plugins {
    id("example.movie.datasoruce")
}



dependencies{
    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))
}

sqldelight{
    database("MovieDatabase"){
        packageName="com.example.movie_datasource.cache"
        sourceFolders= listOf("sqldelight")
    }
}
