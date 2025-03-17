plugins {
    id("java")
    id("files-plugin")
}

/* example of using remote script
val scripPluginVersion = property("plugin.files.version")
apply(from = "https://raw.githubusercontent.com/andrei-anissimov/gradle-scripts/refs/tags/$scripPluginVersion/gradle/filesPlugin.gradle.kts")
*/

group = "io.anisand"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
