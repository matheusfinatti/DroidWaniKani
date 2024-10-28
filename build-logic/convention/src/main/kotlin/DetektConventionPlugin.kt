import buildlogic.configureDetekt
import buildlogic.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            val extension = extensions.getByType<DetektExtension>(DetektExtension::class.java)
            configureDetekt(extension)

            dependencies {
                add("detektPlugins",  libs.findLibrary("detekt-compose").get())
            }
        }
    }
}
