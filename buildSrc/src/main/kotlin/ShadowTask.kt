import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider

fun Project.registerRelocationTask(
    platform: String,
    version: String,
    relocate: Pair<String, String>,
    depVersions: List<String> = emptyList()
): TaskProvider<ShadowJar> {
    val packageBase = "${findProperty("group")}.${findProperty("mod_id")}"
    val packagePath = packageBase.replace('.', '/')

    val versionTag = "v" + version.replace('.', '_')
    val (sourceSuffix, targetSuffix) = relocate
    val taskName = "relocate${platform.replaceFirstChar(Char::uppercase)}Jar"
    val inputTask = "remap${platform.replaceFirstChar(Char::uppercase)}Jar"

    return tasks.register(taskName, ShadowJar::class.java) {
        dependsOn(inputTask)
        from(jarToFiles(inputTask))
        archiveClassifier.set("$platform-relocated")

        dependencies {
            exclude("$packagePath/$versionTag/mixin/$sourceSuffix/**")
        }
        relocate("$packageBase.$versionTag.$sourceSuffix", "$packageBase.$versionTag.$targetSuffix")

        depVersions.forEach { depVersion ->
            val tag = "v" + depVersion.replace('.', '_')
            relocate("$packageBase.$tag.$sourceSuffix", "$packageBase.$tag.$targetSuffix")
        }
    }
}
