import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

data class PlatformConfigurations(
    val mainCompileOnly: Configuration,
    val fabricCompileOnly: Configuration,
    val forgeCompileOnly: Configuration,
    val neoforgeCompileOnly: Configuration,
    val spongeCompileOnly: Configuration,
    val fabricModImplementation: Configuration,
)

fun Project.createPlatformConfigurations(vararg platforms: String): PlatformConfigurations {
    val configurations = this.configurations

    val mainCompileOnly = configurations.maybeCreate("mainCompileOnly")
    configurations.getByName("compileOnly").extendsFrom(mainCompileOnly)

    fun createCompileOnly(name: String): Configuration {
        return if (name in platforms) {
            configurations.maybeCreate("${name}CompileOnly").apply {
                extendsFrom(mainCompileOnly)
            }
        } else {
            configurations.maybeCreate("${name}CompileOnly_dummy").apply {
                isCanBeConsumed = false
                isCanBeResolved = false
            }
        }
    }

    val fabricModImplementation = if ("fabric" in platforms) {
        val modImplementation = configurations.maybeCreate("modImplementation")
        configurations.maybeCreate("fabricModImplementation").apply {
            extendsFrom(modImplementation)
        }
    } else {
        configurations.maybeCreate("fabricModImplementation_dummy").apply {
            isCanBeConsumed = false
            isCanBeResolved = false
        }
    }

    return PlatformConfigurations(
        mainCompileOnly = mainCompileOnly,
        fabricCompileOnly = createCompileOnly("fabric"),
        forgeCompileOnly = createCompileOnly("forge"),
        neoforgeCompileOnly = createCompileOnly("neoforge"),
        spongeCompileOnly = createCompileOnly("sponge"),
        fabricModImplementation = fabricModImplementation
    )
}
