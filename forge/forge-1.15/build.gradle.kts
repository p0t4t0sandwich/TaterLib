import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
        mixinConfig("taterlib.mixins.v1_15.forge.json")
    }
    mappings {
        mojmap()
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
    compileOnly(libs.mixin)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":modapi:conditional-mixins"))
    compileOnly(project(":forge:forge-utils-modern"))
    implementation(project(":vanilla:vanilla-1.15"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.15"))
    }
    relocate("dev.neuralnexus.taterlib.mixin.v1_15.vanilla", "dev.neuralnexus.taterlib.mixin.v1_15.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_15.vanilla", "dev.neuralnexus.taterlib.v1_15.vanilla.forge")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
