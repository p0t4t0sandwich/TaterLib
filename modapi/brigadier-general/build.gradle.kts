import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask
import java.time.Instant

plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
}

subprojects {
    base {
        archivesName = "${modId}-${minecraftVersion}"
    }

    java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
    java.targetCompatibility = JavaVersion.toVersion(javaVersion)

    tasks.withType<RemapJarTask>().configureEach {
        mixinRemap {
            enableBaseMixin()
            disableRefmap()
        }
    }

    var mainCompileOnly = configurations.maybeCreate("mainCompileOnly")

    dependencies {
        compileOnly(rootProject.libs.mixin)
        mainCompileOnly(variantOf(rootProject.libs.modapi.base) { classifier("downgraded-8") })
        mainCompileOnly(variantOf(rootProject.libs.modapi.brigadier) { classifier("downgraded-8") })
        mainCompileOnly(variantOf(rootProject.libs.modapi.metadata) { classifier("downgraded-8") })
        mainCompileOnly(variantOf(rootProject.libs.modapi.muxins) { classifier("downgraded-8") })
    }
}

base {
    archivesName = "brigadier-general"
}

dependencies {
//    compileOnly("com.destroystokyo.paper:paper-api:1.15.2-R0.1-SNAPSHOT")
//    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
//    compileOnly("net.fabricmc:fabric-loader:0.16.9")
//    compileOnly("me.lucko:fabric-permissions-api:0.2-SNAPSHOT")
//    compileOnly("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:1.9.0+1.12.2")
//    compileOnly("org.spongepowered:spongeapi:8.1.0")
//    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    api(libs.brigadier)
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "TaterLib Brigadier General",
                "Specification-Version" to version,
                "Specification-Vendor" to "NeualNexus",
                "Implementation-Version" to version,
                "Implementation-Vendor" to "NeualNexus",
                "Implementation-Timestamp" to Instant.now().toString()
            )
        )
    }
}

tasks.downgradeJar {
    downgradeTo = JavaVersion.VERSION_1_8
    archiveClassifier.set("downgraded-8")
}

tasks.shadeDowngradedApi {
    downgradeTo = JavaVersion.VERSION_1_8
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace("brigadier-general", "dev/neuralnexus/taterapi/brigadiergeneral/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

tasks.downgradeJar.get().dependsOn(tasks.spotlessApply)
tasks.assemble {
    dependsOn(tasks.downgradeJar)
    dependsOn(tasks.shadeDowngradedApi)
}

publishing {
    repositories {
        mavenLocal()
        maven("https://maven.neuralnexus.dev/releases") {
            name = "NeuralNexusReleases"
            credentials {
                username = project.findProperty("neuralNexusUsername") as? String ?: System.getenv("NEURALNEXUS_USERNAME") ?: ""
                password = project.findProperty("neuralNexusPassword") as? String ?: System.getenv("NEURALNEXUS_PASSWORD") ?: ""
            }
        }
        maven("https://maven.neuralnexus.dev/snapshots") {
            name = "NeuralNexusSnapshots"
            credentials {
                username = project.findProperty("neuralNexusUsername") as? String ?: System.getenv("NEURALNEXUS_USERNAME") ?: ""
                password = project.findProperty("neuralNexusPassword") as? String ?: System.getenv("NEURALNEXUS_PASSWORD") ?: ""
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks.downgradeJar.get())
            artifact(tasks.shadeDowngradedApi.get())
        }
    }
}
