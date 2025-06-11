import java.time.Instant

plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
}

base {
    archivesName = modId
    version = properties["modlib_version"].toString()
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    compileOnly(project(":modapi:base"))
    compileOnly(project(":modapi:metadata"))
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.withType<ProcessResources> {
    filesMatching(listOf(
        "fabric.mod.json",
        "pack.mcmeta",
        "META-INF/mods.toml",
        "META-INF/neoforge.mods.toml",
        "META-INF/sponge_plugins.json"
    )) {
        expand(project.properties)
    }
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "TaterLib Muxins",
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
            .replace(modId, "dev/neuralnexus/taterapi/$modId/jvmdg")
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
