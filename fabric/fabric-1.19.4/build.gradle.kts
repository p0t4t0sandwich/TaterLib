import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

import net.fabricmc.loom.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.loom)
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

base {
    archivesName = "${projectId}-fabric-${minecraftVersion}"
}

loom.mixin.useLegacyMixinAp = false

dependencies {
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
    compileOnly(libs.modapi.muxins)
    compileOnly(project(":vanilla:vanilla-1.19"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.19"))
    }
    relocate("dev.neuralnexus.taterlib.v1_19.vanilla", "dev.neuralnexus.taterlib.v1_19.vanilla.fabric")
}

tasks.build {
    dependsOn(tasks.remapJar)
}
