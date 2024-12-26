import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    alias(libs.plugins.spongegradle)
}

base {
    archivesName = "${projectId}-sponge-10"
}

java.disableAutoTargetJvm()

sponge {
    apiVersion(project.apiVersion)
    license(projectLicense)
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin(projectId) {
        version("${minecraftVersion}-${version}")
        displayName(projectName)
        entrypoint("dev.neuralnexus.taterlib.sponge.Sponge${projectName}Plugin")
        description(projectDescription)
        links {
            homepage(homepageUrl)
            source(sourceUrl)
            issues(issueUrl)
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
        dependency("luckperms") {
            version("5.3.0")
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(true)
        }
    }
}

dependencies {
    compileOnly(libs.brigadier)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
}
