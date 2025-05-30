import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

base {
    archivesName = "${projectId}-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

sourceSets {
    create("forge") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

@Suppress("UnstableApiUsage")
configurations {
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("forgeCompileOnly"))
    }
}

// ------------------------------------------- Vanilla -------------------------------------------
unimined.minecraft {
    version(minecraftVersion)
    mappings {
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

tasks.jar {
    archiveClassifier.set("vanilla")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("brigadier_general.mixins.v1_17_1.forge.json")
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapForgeJar") {
    asJar.archiveClassifier.set("forge-remap")
    mixinRemap {
        disableRefmap()
    }
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge")
    dependencies {
        exclude("dev/neuralnexus/modapi/briggen/mixin/v1_17_1/vanilla/**")
    }
    relocate("dev.neuralnexus.modapi.briggen.v1_17_1.vanilla", "dev.neuralnexus.modapi.briggen.v1_17_1.searge")
    relocate("dev.neuralnexus.modapi.briggen.v1_16_5.vanilla", "dev.neuralnexus.modapi.briggen.v1_16_5.searge")
    relocate("dev.neuralnexus.modapi.briggen.v1_14_4.vanilla", "dev.neuralnexus.modapi.briggen.v1_14_4.searge")
}

// ------------------------------------------- Common -------------------------------------------
dependencies {
    // Because gradle does things alphabetically. I hate this
    // evaluationDependsOn(":modapi:brigadier-general:bg-api")
    // TODO: Uncomment when JVMDowngrader is applied to this project

    listOf(
        libs.mixin,
        variantOf(libs.modapi.brigadier) {
            classifier("downgraded-8")
        },
        variantOf(libs.modapi) {
            classifier("downgraded-8")
        }
    ).forEach {
        "mainCompileOnly"(it)
        "forgeCompileOnly"(it)
    }
}

tasks.shadowJar {
    listOf(
        "relocateForgeJar"
    ).forEach {
        dependsOn(it)
        from(jarToFiles(it))
    }
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
