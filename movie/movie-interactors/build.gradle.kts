
apply{
    from("$rootDir/library-build.gradle")//if i made this file a kts then i couldn't add it like this.
}
dependencies{

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.movieDataSource))
    "implementation"(project(Modules.movieDomain))


    "implementation"(Kotlinx.coroutinesCore)

}