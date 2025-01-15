plugins {
    id("maven-publish")
    alias(libs.plugins.spotless) apply(false)
}

base {
    archivesName = "entrypoint-spoof"
}

dependencies {
    compileOnly("com.mojang:authlib:1.5.25")
}

java.withSourcesJar()

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
        register("originalJar", MavenPublication::class) {
            artifact(tasks["jar"])
        }
        register("sourcesJar", MavenPublication::class) {
            artifact(tasks["sourcesJar"])
        }
    }
}
