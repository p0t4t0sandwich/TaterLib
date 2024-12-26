import java.time.Instant

plugins {
    id("maven-publish")
}

base {
    archivesName = "muxins"
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly("dev.neuralnexus.modapi:metadata:0.1.0:downgraded-8")
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
                        "Specification-Title" to "Muxins",
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
            artifact(tasks["sourcesJar"])
        }
    }
}

