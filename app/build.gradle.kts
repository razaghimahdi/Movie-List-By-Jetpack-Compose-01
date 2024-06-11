plugins {
    id("example.android.application")
    id("example.android.application.compose")
    id("example.android.hilt")
    id("example.android.build.flavor")
}

android {
    namespace = libs.versions.applicationId.get()

    defaultConfig {
        applicationId =  libs.versions.applicationId.get()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

    }


    buildTypes {
        getByName("debug") {
            manifestPlaceholders["backup"] = "true"
        }

        getByName("release") {
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["backup"] = "true"
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":movie:movie-domain"))
    implementation(project(":movie:movie-interactors"))
    implementation(project(":movie:ui-movieDetail"))
    implementation(project(":movie:ui_movieList"))
    implementation(project(":movie:movie-datasource-test"))



    implementation(libs.sqldelight.android.driver)


    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    implementation(libs.material)
    implementation(libs.appcompat)

    implementation(libs.core.ktx)

    implementation(libs.lifecycler)
    implementation(libs.activity)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.testing)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}