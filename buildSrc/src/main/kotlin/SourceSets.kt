import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.plugins.JavaPluginExtension
import java.io.File

data class PlatformSourceSets(
    val fabric: SourceSet,
    val forge: SourceSet,
    val neoforge: SourceSet,
    val sponge: SourceSet
)

fun Project.createPlatformSourceSets(vararg names: String): PlatformSourceSets {
    val java = extensions.getByType(JavaPluginExtension::class.java)

    val dummy = java.sourceSets.findByName("dummy") ?: run {
        val ds = java.sourceSets.create("dummy")
        ds.java.setSrcDirs(emptySet<File>())
        ds.resources.setSrcDirs(emptySet<File>())
        ds
    }

    fun createOrDummy(name: String): SourceSet =
        if (name in names) java.sourceSets.maybeCreate(name) else dummy

    return PlatformSourceSets(
        fabric = createOrDummy("fabric"),
        forge = createOrDummy("forge"),
        neoforge = createOrDummy("neoforge"),
        sponge = createOrDummy("sponge")
    )
}
