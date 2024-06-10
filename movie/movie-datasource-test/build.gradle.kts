plugins {
    id("example.movie.datasoruce")
}




dependencies{
    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-datasource"))
}
