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
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":modapi:muxins"))
    compileOnly(project(":vanilla:vanilla-1.20"))
    implementation(project(":vanilla:vanilla-1.20.2"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.20.2"))
    }
    relocate("dev.neuralnexus.taterlib.v1_20.vanilla", "dev.neuralnexus.taterlib.v1_20.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla", "dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_20_2.vanilla", "dev.neuralnexus.taterlib.v1_20_2.vanilla.fabric")
}

tasks.named<RemapJarTask>("remapJar") {
    dependsOn(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks.build {
    dependsOn(tasks.remapJar)
}
