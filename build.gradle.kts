import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.io.path.appendText

plugins {
    id("java")
}

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

tasks.register("sortFiles") {
    group = "files"
    description = """
        Sorts files in the given directory into build/files subdirectories based on the sorting type.
        Supported sorting types: [timestamp,extension] 
        """.trimIndent()

    val filesFolder = project.findProperty("tasks.files.folder")
    val filesSortType = project.findProperty("tasks.files.sortType")

    logger.info("==================== Sort Files Task ====================")
    logger.info("Properties:")
    logger.info("tasks.files.folder: $filesFolder")
    logger.info("tasks.files.sortType: $filesSortType")
    logger.info("=========================================================")

    val projectDir = layout.projectDirectory
    val buildDir = layout.buildDirectory

    val inputDir = projectDir.dir("$filesFolder")

    val keySelector : (File) -> String = when(filesSortType) {
        "date" ->  {file -> creationDate(file.lastModified()) }
        else -> { file -> file.extension }
    }

    val groupedFiles = inputDir.asFileTree.files.groupBy { keySelector.invoke(it) }

    groupedFiles.forEach { (key, files) ->
//        val dest = buildDir.dir("$filesFolder/$key").get().asFile.toPath()
        files.forEach { file ->
            val dest = buildDir.dir("$filesFolder/$key/${file.name}").get().asFile.toPath()
            Files.createDirectories(dest.parent)
            Files.copy(file.toPath(), dest, StandardCopyOption.REPLACE_EXISTING)
//            copy {
//                from(file)
//                into(buildDir.dir("$filesFolder/$key"))
//            }
        }
    }
}

tasks.register<Delete>("cleanFiles") {
    group = "files"
    description = "clean build/files directory"

    logger.info("==================== Clean Files Task ====================")
    val filesFolder = project.findProperty("tasks.files.folder")
    val outputDir = layout.buildDirectory.dir("$filesFolder")
    delete(outputDir)
    logger.info("==========================================================")
}

fun creationDate(lastModified: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return Instant.ofEpochMilli(lastModified)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}