import java.time.Instant

plugins {
    id("maven-publish")
    alias(libs.plugins.jvmdowngrader)
    id(libs.plugins.shadow.get().pluginId)
}

base {
    archivesName = "modapi"
}

dependencies {
    compileOnly(project(":modapi:entrypoint-spoof"))

//    implementation(project(":modapi:base"))
//    implementation(project(":modapi:core"))
//    implementation(project(":modapi:crossperms"))
//    implementation(project(":modapi:brigadier-general:bg-api"))
//    implementation(project(":modapi:brigadier-general:bg1_14_4"))
//    implementation(project(":modapi:brigadier-general:bg1_16_5"))
//    implementation(project(":modapi:brigadier-general:bg1_17_1"))
//    implementation(project(":modapi:brigadier-general:bg1_19_4"))
//    implementation(project(":modapi:metadata"))
//    implementation(project(":modapi:muxins"))
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.shadowJar {
    archiveClassifier.set("")
    mergeServiceFiles()

    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "TaterLib ModAPI",
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
    downgradeTo = JavaVersion.VERSION_1_8
    archiveClassifier.set("downgraded-8")
}

tasks.shadeDowngradedApi {
    downgradeTo = JavaVersion.VERSION_1_8
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace("modapi", "dev/neuralnexus/modapi/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

//tasks.downgradeJar.get().dependsOn(tasks.spotlessApply)
//tasks.assemble {
//    dependsOn(tasks.downgradeJar)
//    dependsOn(tasks.shadeDowngradedApi)
//}

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
