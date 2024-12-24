plugins {
    id("maven-publish")
    alias(libs.plugins.spotless) apply(false)
}

base {
    archivesName = "entrypoint-spoof"
}

java.withSourcesJar()

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
