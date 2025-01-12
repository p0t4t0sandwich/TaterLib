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
    // Lucko's Permission API
    "include"("modImplementation"("me.lucko:fabric-permissions-api:0.2-SNAPSHOT") as Any)

    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.muxins) {
        classifier("downgraded-8-all")
    })
    compileOnly(project(":vanilla:vanilla-1.21"))
}

tasks.jar {
    enabled = false
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("dev.neuralnexus.taterlib.mixin.v1_21.vanilla", "dev.neuralnexus.taterlib.mixin.v1_21.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_21.vanilla", "dev.neuralnexus.taterlib.v1_21.vanilla.fabric")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
