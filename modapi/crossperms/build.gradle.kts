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

    // CrossPerms
    compileOnly("com.destroystokyo.paper:paper-api:1.15.2-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    compileOnly("net.fabricmc:fabric-loader:0.16.9")
    compileOnly("me.lucko:fabric-permissions-api:0.2-SNAPSHOT")
    compileOnly("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:1.9.0+1.12.2")
//    compileOnly("org.spongepowered:spongeapi:8.1.0")
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly("com.mojang:authlib:3.13.56")

    // CrossPerms Integrations
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-core:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-bukkit:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-bungee:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-fabric:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-sponge:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-velocity:2.0-SNAPSHOT")

    compileOnly(project(":modapi:entrypoint-spoof"))
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
