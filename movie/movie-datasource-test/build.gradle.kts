
apply{
    from("$rootDir/library-build.gradle")//if i made this file a kts then i couldn't add it like this.
}

plugins{
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(SqlDelight.plugin)
}


dependencies{
    "implementation"(project(Modules.movieDomain))
    "implementation"(project(Modules.movieDataSource))
    "implementation"(project(Modules.core))
    "implementation"(Ktor.core)
    "implementation"(Ktor.ktorClientMock)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)


}
