package com.example.movielistbycompose01

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project


internal fun Project.configureBuildFlavors(
    commonExtension: ApplicationExtension,
) {
    commonExtension.apply {
        flavorDimensions.add(libs.findVersion("applicationId").get().toString())

        productFlavors {
            create(Flavors.Staging.name) {
                applicationIdSuffix = Flavors.Staging.suffix
                versionCode = libs.findVersion("versionCode").get().toString().toInt()
                dimension = libs.findVersion("applicationId").get().toString()
            }

            create(Flavors.Uat.name) {
                applicationIdSuffix = Flavors.Uat.suffix
                versionCode = libs.findVersion("versionCode").get().toString().toInt()
                dimension = libs.findVersion("applicationId").get().toString()
            }

            create(Flavors.Production.name) {
                dimension = libs.findVersion("applicationId").get().toString()
            }
        }
    }
}