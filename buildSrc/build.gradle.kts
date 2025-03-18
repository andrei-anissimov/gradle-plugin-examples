plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("myPlugins") {
            id = "hello-world-2"
            implementationClass = "io.anisand.plugins.HelloWorld2Plugin"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text:1.10.0")
}