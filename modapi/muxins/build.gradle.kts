import xyz.wagyourtail.jvmdg.gradle.task.DowngradeJar
import xyz.wagyourtail.jvmdg.gradle.task.ShadeJar

import java.time.Instant

plugins {
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
    alias(libs.plugins.shadow)
}

base {
    archivesName = "muxins"
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":modapi:entrypoint-spoof"))
    implementation("dev.neuralnexus.modapi:metadata:0.1.0")
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

tasks.downgradeJar {
    inputFile.set(tasks.jar.get().archiveFile)
    downgradeTo.set(JavaVersion.VERSION_1_8)
    archiveClassifier.set("downgraded-8")
}

tasks.shadeDowngradedApi {
    inputFile.set(tasks.downgradeJar.get().archiveFile)
    downgradeTo.set(JavaVersion.VERSION_1_8)
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace("muxins", "dev/neuralnexus/modapi/muxins/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.register<DowngradeJar>("customDowngradeJar") {
    inputFile.set(tasks.shadowJar.get().archiveFile)
    downgradeTo.set(JavaVersion.VERSION_1_8)
    archiveClassifier.set("downgraded-8-custom")
}

tasks.register<ShadeJar>("customShadeDowngradedApi") {
    inputFile.set(tasks.named<DowngradeJar>("customDowngradeJar").get().archiveFile)
    downgradeTo.set(JavaVersion.VERSION_1_8)
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace("muxins", "dev/neuralnexus/modapi/muxins/jvmdg")
    }
    archiveClassifier.set("downgraded-8-all")
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

tasks.jar.get().dependsOn(tasks.spotlessApply)
tasks.downgradeJar.get().dependsOn(tasks.jar)
tasks.shadeDowngradedApi.get().dependsOn(tasks.downgradeJar)
tasks["customDowngradeJar"].dependsOn(tasks.shadowJar)
tasks["customShadeDowngradedApi"].dependsOn(tasks["customDowngradeJar"])
tasks.assemble {
    dependsOn(tasks.downgradeJar)
    dependsOn(tasks.shadeDowngradedApi)
    dependsOn(tasks["customShadeDowngradedApi"])
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
            artifact(tasks["customShadeDowngradedApi"])
        }
    }
}
