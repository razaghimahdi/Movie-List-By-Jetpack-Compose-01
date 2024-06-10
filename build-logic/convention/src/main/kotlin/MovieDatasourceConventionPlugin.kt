
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import com.example.movielistbycompose01.libs

class MovieDatasourceConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("example.android.library")
                apply("app.cash.sqldelight")
            }


            dependencies {

                add("implementation", libs.findBundle("network").get())
                add("implementation", libs.findLibrary("sqldelight.runtime").get())

            }

        }
    }

}