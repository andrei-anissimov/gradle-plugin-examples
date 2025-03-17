package io.anisand.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloWorldPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello-world") {
            project.logger.quiet("Hello, World!")
        }
    }
}