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
        mixinConfig("taterlib.mixins.v1_20.forge.json")
    }
    mappings {
        mojmap()
        devFallbackNamespace("searge")
    }
    defaultRemapJar = false
    remap(tasks.shadowJar.get()) {
        prodNamespace("searge")
        mixinRemap {
            disableRefmap()
        }
    }
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.muxins) {
        classifier("downgraded-8-all")
    })
    compileOnly(project(":forge:forge-utils-modern"))
    compileOnly(project(":forge:forge-1.20.2"))
    implementation(project(":vanilla:vanilla-1.20"))
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("dev.neuralnexus.taterlib.mixin.v1_20.vanilla", "dev.neuralnexus.taterlib.mixin.v1_20.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_20.vanilla", "dev.neuralnexus.taterlib.v1_20.vanilla.forge")
}

//tasks.compileJava.get().dependsOn(project(":forge:forge-1.20.2").tasks.named("remapShadowJar"))
tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
