plugins {
    id("example.android.library")
}

dependencies{

    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-datasource"))

    testImplementation(libs.junit)
    testImplementation(libs.ktor.client.mock)
    testImplementation(project(":movie:movie-datasource-test"))
    implementation(libs.bundles.kotlin)

}