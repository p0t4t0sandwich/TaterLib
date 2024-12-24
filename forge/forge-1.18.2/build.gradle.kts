import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
    }
    mappings {
        mojmap()
    }
    defaultRemapJar = false
    remap(tasks.shadowJar.get()) {
        prodNamespace("searge")
        archiveFileName = "${projectId}-forge-${minecraftVersion}-${project.version}.jar"
    }
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":vanilla:vanilla-1.18"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.18"))
    }
    relocate("dev.neuralnexus.taterlib.v1_18.vanilla", "dev.neuralnexus.taterlib.v1_18.vanilla.forge")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
