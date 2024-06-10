pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Movie List By Compose 01"
include(":app")
include(":core")
include(":components")
include(":movie")
include(":movie:movie-domain")
include(":movie:movie-datasource")
include(":movie:movie-interactors")
include(":movie:ui_movieList")
include(":movie:ui-movieDetail")
include(":movie:movie-datasource-test")
