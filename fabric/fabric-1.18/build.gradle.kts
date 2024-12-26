import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

import net.fabricmc.loom.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.loom)
}

base {
    archivesName = "${projectId}-fabric-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

loom.mixin.useLegacyMixinAp = false

dependencies {
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")

    val apiModules = listOf(
        "fabric-command-api-v1",
        "fabric-lifecycle-events-v1",
        "fabric-networking-api-v1"
    )

    apiModules.forEach {
        modImplementation(fabricApi.module(it, apiVersion))
    }

    // Lucko's Permissions API
    modImplementation("me.lucko:fabric-permissions-api:0.2-SNAPSHOT")

    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
    compileOnly(libs.modapi.muxins)
    implementation(project(":vanilla:vanilla-1.18"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.18"))
    }
    relocate("dev.neuralnexus.taterlib.mixin.v1_18.vanilla", "dev.neuralnexus.taterlib.mixin.v1_18.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_18.vanilla", "dev.neuralnexus.taterlib.v1_18.vanilla.fabric")
}

tasks.named<RemapJarTask>("remapJar") {
    dependsOn(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks.build {
    dependsOn(tasks.remapJar)
}
