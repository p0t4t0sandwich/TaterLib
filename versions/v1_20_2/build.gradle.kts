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
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_20_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.y_intmdry")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_20_2.forge.json")
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
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_20_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.searge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.searge")
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
        project(":versions:v1_14_4"),
        project(":versions:v1_16_1")
    ).forEach {
        "mainCompileOnly"(it)
        "fabricCompileOnly"(it)
        "forgeCompileOnly"(it)
    }

    listOf(
        "fabric-api-base",
        "fabric-command-api-v2",
        "fabric-lifecycle-events-v1",
        "fabric-networking-api-v1"
    ).forEach {
        "fabricModImplementation"(fabricApi.fabricModule(it, fabricVersion))
    }

    "forgeCompileOnly"(files(rootProject.project(":versions:v1_20_1").sourceSets.getByName("forge").java.srcDirs))
    "forgeCompileOnly"(project(":forge:forge-utils-modern"))
}

tasks.named<ShadowJar>("shadowJar") {
    from(tasks.getByName("relocateFabricJar").outputs)
    from(tasks.getByName("relocateForgeJar").outputs)
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
