import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.movielistbycompose01.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "example.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "example.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "example.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "example.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jetbrainsKotlin") {
            id = "example.android.jetbrains.kotlin"
            implementationClass = "JvmLibraryConventionPlugin"
        }
         register("androidComponents") {
            id = "example.android.components"
            implementationClass = "AndroidComponentsConventionPlugin"
        }
         register("movieDatasource") {
            id = "example.movie.datasoruce"
            implementationClass = "MovieDatasourceConventionPlugin"
        }
        register("buildFlavor") {
            id = "example.android.build.flavor"
            implementationClass = "BuildFlavorsConventionPlugin"
        }
        register("androidHilt") {
            id = "example.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}