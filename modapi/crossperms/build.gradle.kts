import xyz.wagyourtail.jvmdg.gradle.task.DowngradeJar
import xyz.wagyourtail.jvmdg.gradle.task.ShadeJar

import java.time.Instant

plugins {
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
}

base {
    archivesName = "crossperms"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    compileOnly("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:1.9.0+1.12.2")
    compileOnly("me.lucko:fabric-permissions-api:0.2-SNAPSHOT")
    compileOnly("org.spongepowered:spongeapi:8.1.0")
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly(project(":modapi:entrypoint-spoof"))
    implementation("dev.neuralnexus.modapi:metadata:0.1.0")
}

java {
    withSourcesJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "CrossPerms",
                "Specification-Version" to version,
                "Specification-Vendor" to "NeualNexus",
                "Implementation-Version" to version,
                "Implementation-Vendor" to "NeualNexus",
                "Implementation-Timestamp" to Instant.now().toString()
            )
        )
    }

    from("README.md") {
        into("META-INF")
    }
}

tasks.named<DowngradeJar>("downgradeJar") {
    downgradeTo = JavaVersion.VERSION_1_8
    archiveClassifier.set("downgraded-8")
}

tasks.named<ShadeJar>("shadeDowngradedApi") {
    downgradeTo = JavaVersion.VERSION_1_8
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace("crossperms", "dev/neuralnexus/modapi/crossperms/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.downgradeJar {
    dependsOn(tasks.spotlessApply)
}

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
                username = (project.findProperty("neuralNexusUsername") ?: System.getenv("NEURALNEXUS_USERNAME")).toString()
                password = (project.findProperty("neuralNexusPassword") ?: System.getenv("NEURALNEXUS_PASSWORD")).toString()
            }
        }
        maven("https://maven.neuralnexus.dev/snapshots") {
            name = "NeuralNexusSnapshots"
            credentials {
                username = (project.findProperty("neuralNexusUsername") ?: System.getenv("NEURALNEXUS_USERNAME")).toString()
                password = (project.findProperty("neuralNexusPassword") ?: System.getenv("NEURALNEXUS_PASSWORD")).toString()
            }
        }
    }

    publications {
        register("originalJar", MavenPublication::class) {
            artifact(tasks["jar"])
        }
        register("sourcesJar", MavenPublication::class) {
            artifact(tasks["sourcesJar"]) {
                classifier = "sources"
            }
        }
        register("downgradedJar", MavenPublication::class) {
            artifact(tasks["downgradeJar"]) {
                classifier = "downgraded-8"
            }
        }
        register("shadedDowngradedJar", MavenPublication::class) {
            artifact(tasks["shadeDowngradedApi"]) {
                classifier = "downgraded-8-shaded"
            }
        }
    }
}
