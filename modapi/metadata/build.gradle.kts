import xyz.wagyourtail.jvmdg.gradle.task.DowngradeJar

import java.time.Instant

plugins {
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
}

base {
    archivesName = "metadata"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":modapi:entrypoint-spoof"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    maxHeapSize = "1G"
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
                "Specification-Title" to "Metadata API",
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

//tasks.withType<GenerateModuleMetadata> {
//    enabled = false
//}
//
tasks.build {
    dependsOn(tasks.test)
}

tasks.shadeDowngradedApi {
    dependsOn(tasks.spotlessApply)
}

tasks.assemble {
    dependsOn(tasks.downgradeJar)
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
    }
}
