import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.bundling.Jar
import xyz.wagyourtail.unimined.util.sourceSets

object Tater

fun Project.jarToFiles(taskName: String): FileCollection {
    val jar: Jar = when (val task = tasks.getByName(taskName)) {
        is Jar -> task
        else -> throw IllegalArgumentException("Task $taskName is not a Jar task")
    }
    return zipTree(jar.archiveFile.get().asFile)
}

val Project.srcSetAsDep: (String, String) -> FileCollection get() = { projName, srcSetName ->
    files(rootProject.project(projName).sourceSets.getByName(srcSetName).output)
}
