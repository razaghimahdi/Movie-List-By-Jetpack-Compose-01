
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import com.example.movielistbycompose01.libs

class MovieDatasourceConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("example.android.application")
                apply("com.squareup.sqldelight")
            }


            dependencies {

                add("implementation", libs.findLibrary("ktor.client.core").get())
                add("implementation", libs.findLibrary("ktor.client.android").get())
                add("implementation", libs.findLibrary("ktor.client.serialization").get())
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                add("implementation", libs.findLibrary("ktor.client.mock").get())
                add("implementation", libs.findLibrary("sqldelight.runtime").get())

            }
        }
    }

}