import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.bundling.Jar
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask
import xyz.wagyourtail.unimined.util.sourceSets

object Tater

val Project.projectName: String get() = properties["project_name"].toString()
val Project.projectId: String get() = properties["project_id"].toString()
val Project.projectDescription: String get() = properties["project_description"].toString()
val Project.projectLicense: String get() = properties["license"].toString()

val Project.apiVersion: String get() = properties["api_version"].toString()
val Project.loaderVersion: String get() = properties["loader_version"].toString()

val Project.jarToFiles: (String) -> FileTree get() = {
    val jar: Jar = when (val task = tasks.getByName(it)) {
        is Jar -> task
        is RemapJarTask -> task.asJar
        else -> throw IllegalArgumentException("Task $it is not a Jar task")
    }
    zipTree(jar.archiveFile.get().asFile)
}

val Project.srcSetAsDep: (String, String) -> ConfigurableFileCollection get() = { projName, srcSetName ->
    files(rootProject.project(projName).sourceSets.getByName(srcSetName).output)
}
