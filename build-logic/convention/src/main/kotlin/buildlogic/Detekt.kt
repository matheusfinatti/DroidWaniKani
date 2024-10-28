package buildlogic

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project

internal fun Project.configureDetekt(extension: DetektExtension) = extension.apply {
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = false
    parallel = true
    config.setFrom("${rootDir.path}/config/detekt/detekt.yml")

    tasks.withType<Detekt>(Detekt::class.java) {
        reports {
            html.required.set(true)
            xml.required.set(true)
            md.required.set(true)
        }
    }
}
