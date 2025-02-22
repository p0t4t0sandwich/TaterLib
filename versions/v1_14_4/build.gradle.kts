import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
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
    val mainImplementation by creating
    named("implementation") {
        extendsFrom(configurations.getByName("fabricImplementation"))
        extendsFrom(configurations.getByName("forgeImplementation"))
    }
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("fabricCompileOnly"))
        extendsFrom(configurations.getByName("forgeCompileOnly"))
    }
    val modImplementation by creating
    named("modImplementation") {
        extendsFrom(configurations.getByName("fabricImplementation"))
        extendsFrom(configurations.getByName("forgeImplementation"))
    }
}

sourceSets {
    named("main") {
        compileClasspath += configurations.getByName("mainImplementation")
        runtimeClasspath += configurations.getByName("mainImplementation")

        compileClasspath += configurations.getByName("mainCompileOnly")
        runtimeClasspath += configurations.getByName("mainCompileOnly")
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

tasks.named<ShadowJar>("shadowJar") {
    from(tasks.getByName("relocateFabricJar").outputs)
    from(tasks.getByName("relocateForgeJar").outputs)
    archiveClassifier.set("")
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
    relocate("dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla", "dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.fabric")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.vanilla.fabric")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_14_4.forge.json")
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
    relocate("dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla", "dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.forge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.vanilla.forge")
}

// ------------------------------------------- Common -------------------------------------------
dependencies {
    "mainCompileOnly"(libs.mixin)
    "mainCompileOnly"(project(":api"))
    "mainCompileOnly"(project(":common"))
    "mainCompileOnly"(project(":loader"))
    "mainCompileOnly"(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })

    "fabricCompileOnly"(libs.mixin)
    "fabricCompileOnly"(project(":api"))
    "fabricCompileOnly"(project(":common"))
    "fabricCompileOnly"(project(":loader"))
    "fabricCompileOnly"(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })

    "forgeCompileOnly"(libs.mixin)
    "forgeCompileOnly"(project(":api"))
    "forgeCompileOnly"(project(":common"))
    "forgeCompileOnly"(project(":loader"))
    "forgeCompileOnly"(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })

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

tasks.build {
    dependsOn(tasks.shadowJar)
}
