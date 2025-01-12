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
    val apiModules = listOf(
        "fabric-command-api-v2",
        "fabric-lifecycle-events-v1",
        "fabric-networking-api-v1"
    )

    apiModules.forEach {
        "modImplementation"(fabricApi.fabricModule(it, apiVersion))
    }

    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.muxins) {
        classifier("downgraded-8-all")
    })
    implementation(project(":vanilla:vanilla-1.19"))
}

tasks.jar {
    enabled = false
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("dev.neuralnexus.taterlib.mixin.v1_19.vanilla", "dev.neuralnexus.taterlib.mixin.v1_19.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_19.vanilla", "dev.neuralnexus.taterlib.v1_19.vanilla.fabric")
}

tasks.build {
    dependsOn(tasks.named("remapShadowJar"))
}
