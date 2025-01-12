import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-fabric-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    fabric {
        loader(loaderVersion)
    }
    mappings {
        intermediary()
        mojmap()
        devFallbackNamespace("intermediary")
    }
    defaultRemapJar = false
    remap(tasks.shadowJar.get()) {
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
    compileOnly(project(":vanilla:vanilla-1.20"))
    implementation(project(":vanilla:vanilla-1.20.2"))
}

tasks.jar {
    enabled = false
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("dev.neuralnexus.taterlib.v1_20.vanilla", "dev.neuralnexus.taterlib.v1_20.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla", "dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_20_2.vanilla", "dev.neuralnexus.taterlib.v1_20_2.vanilla.fabric")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
