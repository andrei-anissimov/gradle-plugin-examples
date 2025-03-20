pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        maven {
            url = java.net.URI("http://localhost:8080/repository/internal")
            name = "privateArchivaMaven"
            isAllowInsecureProtocol = true
            val privateArchivaUser: String? by settings
            val privateArchivaPassword: String? by settings
            credentials {
                username = privateArchivaUser
                password = privateArchivaPassword
            }
        }
    }
}
rootProject.name = "gradle-plugin-examples"

