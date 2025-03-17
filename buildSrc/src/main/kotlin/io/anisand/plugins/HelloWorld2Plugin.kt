package io.anisand.plugins

import org.apache.commons.text.WordUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloWorld2Plugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello-world-2") {
            doLast {
                project.logger.info(WordUtils.capitalize("hello, world!"))
            }
        }
    }
}