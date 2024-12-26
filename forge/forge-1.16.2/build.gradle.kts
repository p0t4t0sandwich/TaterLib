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
        mixinConfig("taterlib.mixins.v1_16_2.forge.json")
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
    compileOnly(project(":loader"))
    compileOnly(project(":modapi:conditional-mixins"))
    compileOnly(project(":forge:forge-utils-modern"))
    implementation(project(":vanilla:vanilla-1.16"))
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(project(":vanilla:vanilla-1.16"))
    }
    relocate("dev.neuralnexus.taterlib.mixin.v1_16.vanilla", "dev.neuralnexus.taterlib.mixin.v1_16.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_16.vanilla", "dev.neuralnexus.taterlib.v1_16.vanilla.forge")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
