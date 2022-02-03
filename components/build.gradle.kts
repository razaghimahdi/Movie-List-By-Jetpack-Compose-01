apply{
    from("$rootDir/android-library-build.gradle")//if i made this file a kts then i couldn't add it like this.
}
dependencies{

    "implementation"(project(Modules.core))

}