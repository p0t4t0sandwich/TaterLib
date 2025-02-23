import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.unimined.api.UniminedExtension
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

sourceSets {
    create("fabric") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
    create("forge") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

@Suppress("UnstableApiUsage")
configurations {
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("fabricCompileOnly"))
        extendsFrom(configurations.getByName("forgeCompileOnly"))
    }
    val modImplementation by creating
    named("modImplementation") {
        extendsFrom(configurations.getByName("fabricImplementation"))
    }
}

// ------------------------------------------- Vanilla -------------------------------------------
unimined.minecraft {
    version(minecraftVersion)
    mappings {
        mojmap()
        devFallbackNamespace("official")

        // Fix from https://github.com/CDAGaming/CraftPresence-Mirror/blob/0388d7bdc99440a3c2c317ab59cf728c9033b5da/build.gradle.kts#L253
        stub.withMappings("searge", "mojmap") {
            c("ModLoader", "net/minecraft/src/ModLoader", "net/minecraft/src/ModLoader")
            c("BaseMod", "net/minecraft/src/BaseMod", "net/minecraft/src/BaseMod")
            c(
                "dng",
                listOf(
                    "net/minecraft/client/gui/widget/Widget",
                    "net/minecraft/client/gui/components/AbstractWidget"
                )
            ) {
                m("e", "()I", "func_238483_d_", "getHeightRealms")
            }
        }
    }
    defaultRemapJar = false
}

tasks.jar {
    archiveClassifier.set("vanilla")
}

// ------------------------------------------- Fabric -------------------------------------------
unimined.minecraft(sourceSets.getByName("fabric")) {
    combineWith(sourceSets.main.get())
    fabric {
        loader(fabricLoaderVersion)
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapFabricJar") {
    asJar.archiveClassifier.set("fabric-remap")
    mixinRemap {
        disableRefmap()
    }
}

tasks.create<ShadowJar>("relocateFabricJar") {
    from(tasks.getByName<RemapJarTask>("remapFabricJar").outputs)
    archiveClassifier.set("fabric")
    relocate("dev.neuralnexus.taterlib.mixin.v1_16_1.vanilla", "dev.neuralnexus.taterlib.mixin.v1_16_1.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.vanilla.fabric")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_16_1.forge.json")
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapForgeJar") {
    asJar.archiveClassifier.set("forge-remap")
    mixinRemap {
        disableRefmap()
    }
}

tasks.create<ShadowJar>("relocateForgeJar") {
    from(tasks.getByName<RemapJarTask>("remapForgeJar").outputs)
    archiveClassifier.set("forge")
    relocate("dev.neuralnexus.taterlib.mixin.v1_16_1.vanilla", "dev.neuralnexus.taterlib.mixin.v1_16_1.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.vanilla.forge")
}

// ------------------------------------------- Common -------------------------------------------
dependencies {
    listOf(
        libs.mixin,
        project(":api"),
        project(":common"),
        variantOf(libs.modapi) {
            classifier("downgraded-8")
        },
        project(":versions:v1_14_4")
    ).forEach {
        "mainCompileOnly"(it)
        "fabricCompileOnly"(it)
        "forgeCompileOnly"(it)
    }

    listOf(
        "fabric-api-base",
        "fabric-command-api-v1",
        "fabric-lifecycle-events-v1",
        "fabric-networking-api-v1"
    ).forEach {
        "fabricModImplementation"(fabricApi.fabricModule(it, fabricVersion))
    }

    "forgeCompileOnly"(project(":forge:forge-utils-modern"))
}

tasks.named<ShadowJar>("shadowJar") {
    from(tasks.getByName("relocateFabricJar").outputs)
    from(tasks.getByName("relocateForgeJar").outputs)
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
