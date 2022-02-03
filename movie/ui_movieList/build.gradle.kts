apply{
    from("$rootDir/android-library-build.gradle")//if i made this file a kts then i couldn't add it like this.
}
dependencies{

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.components))
    "implementation"(project(Modules.movieDomain))
    "implementation"(project(Modules.movieInteractors))


    "implementation"(Coil.coil)

    "implementation"(SqlDelight.androidDriver)

    "implementation"(Hilt.android)
    "kapt"(Hilt.compiler)


}