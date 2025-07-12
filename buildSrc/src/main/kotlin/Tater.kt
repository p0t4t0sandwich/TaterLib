import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.SourceSet

import java.io.File

fun Project.jarToFiles(taskName: String): FileCollection {
    val jar: Jar = when (val task = tasks.getByName(taskName)) {
        is Jar -> task
        else -> throw IllegalArgumentException("Task $taskName is not a Jar task")
    }
    return zipTree(jar.archiveFile.get().asFile)
}

val Project.srcSetAsDep: (String, String) -> FileCollection get() = { projName, srcSetName ->
    files(rootProject.project(projName).extensions.getByType(JavaPluginExtension::class.java).sourceSets.getByName(srcSetName).output)
}

data class Platform(
    val sourceSet: SourceSet,
    val compileOnly: Configuration
)

data class FabricPlatform(
    val sourceSet: SourceSet,
    val compileOnly: Configuration,
    val modImplementation: Configuration
)

data class Platforms(
    val main: Platform,
    val fabric: FabricPlatform,
    val forge: Platform,
    val neoforge: Platform,
    val sponge: Platform
)

fun Project.getPlatforms(vararg platforms: String): Platforms {
    val sourceSets = extensions.getByType(JavaPluginExtension::class.java).sourceSets
    val configurations = this.configurations

    val dummySourceSet = sourceSets.maybeCreate("dummy").apply {
        java.setSrcDirs(emptySet<File>())
        resources.setSrcDirs(emptySet<File>())
    }
    val dummyConfiguration = configurations.maybeCreate("dummyCompileOnly").apply {
        isCanBeConsumed = false
        isCanBeResolved = false
    }
    val dummyPlatform = Platform(dummySourceSet, dummyConfiguration)

    val mainCompileOnly = configurations.maybeCreate("mainCompileOnly")
    configurations.getByName("compileOnly").extendsFrom(mainCompileOnly)
    val main = Platform(sourceSets.getByName("main"), mainCompileOnly)

    fun createOrDummyPlatform(name: String): Platform {
        if (name in platforms) {
            val sourceSet = sourceSets.maybeCreate(name)
            val compileOnly = configurations.maybeCreate("${name}CompileOnly").apply {
                extendsFrom(mainCompileOnly)
            }
            return Platform(sourceSet, compileOnly)
        } else {
            return dummyPlatform
        }
    }

    val fabric = if ("fabric" in platforms) {
        val sourceSet = sourceSets.maybeCreate("fabric")
        val compileOnly = configurations.maybeCreate("fabricCompileOnly").apply {
            extendsFrom(mainCompileOnly)
        }
        val modImplementation = configurations.maybeCreate("fabricModImplementation")
        FabricPlatform(sourceSet, compileOnly, modImplementation)
    } else {
        FabricPlatform(dummySourceSet, dummyConfiguration, dummyConfiguration)
    }

    return Platforms(
        main = main,
        fabric = fabric,
        forge = createOrDummyPlatform("forge"),
        neoforge = createOrDummyPlatform("neoforge"),
        sponge = createOrDummyPlatform("sponge")
    )
}
