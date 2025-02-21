import java.time.Instant

plugins {
    alias(libs.plugins.jvmdowngrader)
}

base {
    archivesName = "testmod"
}

dependencies {
    compileOnly(libs.brigadier)
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":api"))
    compileOnly(project(":loader"))
    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}

tasks.withType<ProcessResources>().configureEach {
    filesMatching(
        listOf(
            "plugin.yml",
            "bungee.yml",
            "fabric.mod.json",
            "${projectId}.mixins.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "mcmod.info",
            "META-INF/sponge_plugins.json",
            "velocity-plugin.json"
        )
    ) {
        expand(project.properties)
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "TaterLib TestMod",
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
            .replace("metadata", "dev/neuralnexus/modapi/metadata/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

tasks.build.get().dependsOn(tasks.test)
tasks.downgradeJar.get().dependsOn(tasks.spotlessApply)
tasks.assemble {
    dependsOn(tasks.downgradeJar)
    dependsOn(tasks.shadeDowngradedApi)
}
